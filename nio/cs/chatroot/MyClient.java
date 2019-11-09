package nio.cs.chatroot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * NIO网络编程——C/S聊天室
 */
public class MyClient {
    //定义检查SocketChannel的Selector对象
    private Selector selector;

    //定义处理编码和解码的字符集
    private static Charset charset = StandardCharsets.UTF_8;

    //客户端SocketChannel
    private SocketChannel clientSocket;

    public static void main(String[] args) throws IOException {
        new MyClient().startClient();
    }

    public void startClient () throws IOException {
        //1、根据SocketChannel.open(InetSocketAddress isa)方法来获取SocketChannel对象
        // 并设置IP和端口，设置非阻塞模式
        clientSocket = SocketChannel.open(new InetSocketAddress(AddressMessage.IP, AddressMessage.PORT));
        clientSocket.configureBlocking(false);
        //2、根据Selector.open()获取Selector对象
        selector = Selector.open();
        //3、注册SocketChannel到selector中
        clientSocket.register(selector, SelectionKey.OP_READ);
        //4、启动读取服务器端数据的线程
        new ClientThread().start();

        //5、创建键盘输入流，不断向服务端发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            //读取键盘输入
            String input = scanner.nextLine();
            clientSocket.write(charset.encode(input));
        }
    }
    private class ClientThread extends Thread{
        @Override
        public void run () {
            try {
                while (selector.select() > 0) {
                    for (SelectionKey sk : selector.selectedKeys()) {
                        selector.selectedKeys().remove(sk);
                        if (sk.isReadable()) {
                            SocketChannel sc = (SocketChannel)sk.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            StringBuffer content = new StringBuffer("");
                            while (sc.read(buffer) > 0) {
                                buffer.flip();
                                content.append(charset.decode(buffer));
                            }
                            System.out.println("聊天信息 = " + content);
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

