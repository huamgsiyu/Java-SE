package com.syh.udp.net.multicast;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * MulticastSocket实现基于广播的多人聊天室
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        new MyServer().startChat();
    }

    public void startChat () throws IOException {
        //创建MulticastSocket对象，并绑定指定的网络端口
        MulticastSocket sendSocket = new MulticastSocket(AddressMessage.PORT);
        //将MulticastSocket对象加入到多点广播组中
        sendSocket.joinGroup(InetAddress.getByName(AddressMessage.MULTICAST_IP));

        //创建DatagramPacket对象
        DatagramPacket sendPacket = new DatagramPacket(new byte[1024],
                1024,
                InetAddress.getByName(AddressMessage.MULTICAST_IP),
                AddressMessage.PORT);
        //不断发送键盘输入的数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String content = scanner.nextLine();
            sendPacket.setData(content.getBytes());
            sendSocket.send(sendPacket);
        }
    }
}
