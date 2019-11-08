package com.syh.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

/**
 * 服务器类
 */
public class Server {
    public static void main(String[] args) throws IOException{
        try (
                ServerSocket server = new ServerSocket(20000);
                ){
            while (true) {
                Socket socket = server.accept();
                OutputStream os = socket.getOutputStream();
                PrintStream ps = new PrintStream(os);
                ps.println("您好，我是服务端。。。");

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = br.readLine();
                System.out.println("line = " + line);

                ps.close();
                os.close();
                socket.close();
            }
        }
    }
}
