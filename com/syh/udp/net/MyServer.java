package com.syh.udp.net;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * UDP网络编程——作为发送端
 */
public class MyServer {
    private Charset charset = StandardCharsets.UTF_8;
    private DatagramSocket server;

    public static void main(String[] args) throws IOException {
        new MyServer().startServer();
    }

    public void startServer () throws IOException {
        server = new DatagramSocket();

        //发送端准备
        byte[] buff;
        DatagramPacket sendPacket = new DatagramPacket(new byte[0],
                0,
                InetAddress.getByName(AddressMessage.IP),
                AddressMessage.PORT);

        //不断把键盘输入的数据发送到客户端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String content = scanner.nextLine();
            sendPacket.setData(content.getBytes());
            server.send(sendPacket);
        }
    }
}
