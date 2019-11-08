package com.syh.socket;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 20000);
        InputStream is = client.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        System.out.println("line = " + line);

        OutputStream os = client.getOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.println("您好，我是客户端。。。");

        br.close();
        isr.close();
        is.close();
        client.close();

    }
}
