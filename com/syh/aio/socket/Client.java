package com.syh.aio.socket;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * AIO通信——客户端
 */
public class Client {
    static Charset charset = StandardCharsets.UTF_8;

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new InetSocketAddress(AddressMessage.IP, AddressMessage.PORT)).get();

        //获取服务端发送的消息
        StringBuffer stringBuffer = new StringBuffer("");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer).get();
        buffer.flip();
        stringBuffer.append(charset.decode(buffer));
        System.out.println("服务端 = " + stringBuffer);

        //发送消息给服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String message = scanner.nextLine();
            client.write(charset.encode(message));
        }
    }
}
