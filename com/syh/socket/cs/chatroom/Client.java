package com.syh.socket.cs.chatroom;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * C/S聊天室的客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {

        Socket client = new Socket("127.0.0.1", 50001);
        Thread thread = new Thread(new ClientThread(client));
        thread.start();
        PrintStream ps = new PrintStream(client.getOutputStream());

        //读取键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String printMessage = null;
        //输出到服务端
        while ((printMessage = br.readLine()) != null) {
            ps.println(printMessage);
        }
    }

    @Test
    public void test () {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.forEach((obj, obj2) -> System.out.println("obj = " + obj + "obj2 = " + obj2));
    }
}
