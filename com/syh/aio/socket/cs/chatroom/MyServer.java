package com.syh.aio.socket.cs.chatroom;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AIO实现多人聊天工具——服务端
 */
public class MyServer {
    //字符集编码、解码
    private final static Charset charset = StandardCharsets.UTF_8;
    static List<AsynchronousSocketChannel> conns = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        new MyServer().startServer();
        //保证服务器是运行的，不然服务器的其他线程也会关闭
        Thread.sleep(200000);
    }

    private void startServer () throws IOException {
        //1、创建一个线程池对象
        ExecutorService executor = Executors.newFixedThreadPool(20);
        //2、使用AsynchronousChannelGroup管理AsynchronousServerSocketChannel
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(executor);
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group);
        //3、监控指定IP地址和端口
        server.bind(new InetSocketAddress(AddressMessage.IP, AddressMessage.PORT));
        //4、接受客户端的连接
        server.accept(null, new AcceptHandler(server));
    }
    //实现自己的CompletionHandler
    class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
        private AsynchronousServerSocketChannel server;
        private ByteBuffer buff = ByteBuffer.allocate(1024);

        public AcceptHandler(AsynchronousServerSocketChannel server) {
            this.server = server;
        }

        @Override
        public void completed(final AsynchronousSocketChannel sc, Object attachment) {
            MyServer.conns.add(sc);
            server.accept(null, this);
            sc.read(buff,
                    null,
                    new CompletionHandler<Integer, Object>() {
                        @Override
                        public void completed(Integer result, Object attachment) {
                            buff.flip();
                            String content = charset.decode(buff).toString();
                            for (AsynchronousSocketChannel asc : MyServer.conns) {
                                try {
                                    asc.write(charset.encode(content)).get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }
                            buff.clear();
                            sc.read(buff, null, this);
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            System.out.println("读取数据失败：" + exc);
                            MyServer.conns.remove(sc);
                        }
                    });
        }

        @Override
        public void failed(Throwable exc, Object attachment) {
            System.out.println("连接失败：" + exc);
        }
    }
}
