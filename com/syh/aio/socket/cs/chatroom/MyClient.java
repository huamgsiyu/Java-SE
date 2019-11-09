package com.syh.aio.socket.cs.chatroom;

import nio.cs.chatroot.AddressMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AIO实现多人聊天工具——客户端
 */
public class MyClient {
    private Charset charset = StandardCharsets.UTF_8;
    AsynchronousSocketChannel client;
    JFrame mainWin = new JFrame("多人聊天");
    JTextArea jta = new JTextArea(16, 48);
    JTextField jtf = new JTextField(40);
    JButton sendBn = new JButton("发送");

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        MyClient myClient = new MyClient();
        myClient.startClient();
        myClient.connect();
    }

    public void startClient () {
        //界面设置
        mainWin.setLayout(new BorderLayout());
        jta.setEditable(false);
        mainWin.add(new JScrollPane(jta), BorderLayout.CENTER);
        JPanel jp = new JPanel();
        jp.add(jtf);
        jp.add(sendBn);

        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = jtf.getText();
                if (content.trim().length() > 0) {
                    try {
                        client.write(
                                charset.encode(content)).get();
                    } catch (InterruptedException | ExecutionException  ex) {
                        ex.printStackTrace();
                    }
                }
                jtf.setText("");
            }
        };
        sendBn.addActionListener(sendAction);
        //将“Ctrl+Enter”键和"send"关联
        jtf.getInputMap().put(KeyStroke.getKeyStroke('\n',
                InputEvent.CTRL_DOWN_MASK),
                "send");
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.add(jp, BorderLayout.SOUTH);
        mainWin.pack();
        mainWin.setVisible(true);
    }
    public void connect () throws IOException, ExecutionException, InterruptedException {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        ExecutorService executor = Executors.newFixedThreadPool(80);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(executor);
        client = AsynchronousSocketChannel.open(group);
        client.connect(new InetSocketAddress(AddressMessage.IP, AddressMessage.PORT)).get();
        jta.append("---与服务器连接成功---");
        buffer.clear();
        client.read(buffer, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                buffer.flip();
                String content = charset.decode(buffer).toString();
                jta.append(content);
                buffer.clear();
                client.read(buffer, null, this);
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("读取数据失败：" + exc);
            }
        });
    }
}
