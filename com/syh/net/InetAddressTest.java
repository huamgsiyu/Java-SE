package com.syh.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress常用方法测试
 */
public class InetAddressTest {
    public static void main(String[] args) throws Exception {

        InetAddress baidu = InetAddress.getByName("www.baidu.com");

        boolean reachable = baidu.isReachable(5000);
        System.out.println("reachable = " + reachable);     //true

        InetAddress byAddress = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
        boolean reachable1 = byAddress.isReachable(2000);
        System.out.println("reachable1 = " + reachable1);   //true

        String canonicalHostName = byAddress.getCanonicalHostName();
        System.out.println("canonicalHostName = " + canonicalHostName); //127.0.0.1

        String hostAddress = byAddress.getHostAddress();
        System.out.println("hostAddress = " + hostAddress);     //127.0.0.1

        String hostName = byAddress.getHostName();
        System.out.println("hostName = " + hostName);       //127.0.0.1

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost); //UVGGIZNZRJ22H9Q/192.168.190.1
    }
}
