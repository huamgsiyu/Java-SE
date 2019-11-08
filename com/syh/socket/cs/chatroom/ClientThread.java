package com.syh.socket.cs.chatroom;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread implements Runnable{
    private Socket conn = null;
    public ClientThread() {
    }

    public ClientThread(Socket conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String message = null;
            while ((message = br.readLine()) != null) {
                System.out.println(message);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
