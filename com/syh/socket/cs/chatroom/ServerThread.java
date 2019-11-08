package com.syh.socket.cs.chatroom;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ServerThread implements Runnable {
    private Socket conn;

    private Map<Integer, Socket> conns = null;
    //第一次连接上服务端的key
    private Integer key;

    public ServerThread() {
    }

    public ServerThread(Map<Integer, Socket> conns, Socket conn, Integer key) {
        this.conn = conn;
        this.conns = conns;
        this.key = key;
    }

    @Override
    public void run() {
        try {
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String content = null;
            while ((content = readFromClient(br)) != null) {
                //把读取到的消息发送给其他的客户端
                sendMessage(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromClient (BufferedReader br) {
        String message = null;
        try {
            message = br.readLine();
        } catch (IOException e) {
            System.out.println("该客户端已经关闭");
            conns.remove(key);
        }
        return message;
    }

    private void sendMessage (String message) {
        if (conns != null && !conns.isEmpty()) {
            for (Map.Entry<Integer, Socket> obj : conns.entrySet()) {
                if (obj.getKey() == key) {
                    continue;
                }
                Socket client = obj.getValue();
                try {
                    OutputStream os = client.getOutputStream();
                    PrintStream ps = new PrintStream(os);

                    StringBuilder stringBuilder = new StringBuilder("");
                    stringBuilder.append(key);
                    stringBuilder.append(": ");
                    stringBuilder.append(message);

                    ps.println(stringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Socket getSocket() {
        return conn;
    }

    public void setSocket(Socket conn) {
        this.conn = conn;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
