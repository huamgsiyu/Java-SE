package com.syh.udp.net.multicast;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * MulticastSocket实现基于广播的多人聊天室
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        new MyClient().startReceive();
    }

    public void startReceive () throws IOException {
        //创建MulticastSocket
        MulticastSocket receiveSocket = new MulticastSocket(AddressMessage.PORT);
        //加入广播组
        receiveSocket.joinGroup(InetAddress.getByName(AddressMessage.MULTICAST_IP));

        //准备DatagramPacket对象
        byte[] receiveBuff = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuff, receiveBuff.length);
        String content = null;
        while (true) {
            receiveSocket.receive(receivePacket);
            content = new String(receivePacket.getData());
            System.out.println("content = " + content);
        }
    }
}
