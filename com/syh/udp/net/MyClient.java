package com.syh.udp.net;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.net.*;

/**
 * UDP网络编程——作为接收端
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket(AddressMessage.PORT);

        //准备接收数据
        byte[] buff = new byte[1024];
        int length = 1024;
        DatagramPacket packet = new DatagramPacket(buff, length);

        //不断的接受消息
        while (true) {
            client.receive(packet);
            String content = new String(packet.getData());
            System.out.println("接收到服务端发来的消息：" + content);
        }
    }
}
