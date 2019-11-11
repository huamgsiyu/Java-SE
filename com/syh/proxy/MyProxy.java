package com.syh.proxy;

import nio.cs.chatroot.AddressMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

/**
 * 在URLConnection中使用代理服务器
 */
public class MyProxy {

    private final static String URL = "https://www.baidu.com";

    public static void main(String[] args) throws IOException {
        new MyProxy().conn();
    }

    public void conn () throws IOException {
        URL url = new URL(URL);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress(AddressMessage.IP, AddressMessage.PORT));
        URLConnection conn = url.openConnection(proxy);
        conn.setConnectTimeout(3000);
        InputStream inputStream = conn.getInputStream();
        byte[] buff = new byte[1024];
        while (inputStream.read(buff) > 0) {
            System.out.println("message = " + new String(buff));
        }

    }
}
