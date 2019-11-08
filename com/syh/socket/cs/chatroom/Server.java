package com.syh.socket.cs.chatroom;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * C/S聊天室服务端
 *  服务器转为中转站，负责把一个客户端的信息发送给其他所有客户端
 */
public class Server {
    //保存已和服务端建立连接的客户端
    static Map<Integer, Socket> conns = new HashMap<>();

    public static void main(String[] args) {
        ServerSocket server = null;
        int id = 0;
        try {
            server = new ServerSocket(50001);
            while (true) {
                Socket conn = server.accept();
                conns.put(id, conn);
                Thread thread = new Thread(new ServerThread(conns, conn, id));
                thread.start();
                id++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
