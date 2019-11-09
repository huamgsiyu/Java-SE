package com.syh.aio.socket;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AIO异步通信——服务端
 */
public class Server {
    static Charset charset = StandardCharsets.UTF_8;
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        /*
            1、open()方法获取实例
            2、bind()方法监听指定IP地址和端口
            3、accept()方法接受客户端连接
         */
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(AddressMessage.IP, AddressMessage.PORT));
        //不断接受客户端的连接
        while (true) {
            Future<AsynchronousSocketChannel> accept = server.accept();
            AsynchronousSocketChannel socketChannel = accept.get();
            //发送消息给客户端
            socketChannel.write(charset.encode("各位客户端你们好，这里是服务端"));

            //读取客户端发送的消息
            StringBuffer stringBuffer = new StringBuffer("");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer).get() > 0) {
                buffer.flip();
                stringBuffer.append(charset.decode(buffer));
            }
            System.out.println("stringBuffer = " + stringBuffer);

        }

    }
}
