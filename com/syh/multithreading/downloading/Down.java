package com.syh.multithreading.downloading;

import com.syh.tools.DownUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * 多线程下载类
 */
public class Down {

    public static void main(String[] args) throws IOException {

        String spec1 = "https://repo1.maven.org/maven2/org/apache/xmlgraphics/xmlgraphics-commons/2.4/xmlgraphics-commons-2.4.jar";
        String spec = "https://www.cnblogs.com/fwnboke/p/8529492.html";
        String spec2 = "http://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg";
        String spec3 = "http://192.168.190.130:8080/download/aaaaa.cfg";
        DownUtil downUtil = new DownUtil(spec2, "E:\\download\\20120830173930_PBfJE.jpeg");
        downUtil.download();
//        Scanner scnner = new Scanner(System.in);
//        while (scnner.hasNext()) {
//            String spec = scnner.next();
//            spec = spec.trim();
//            urlAvailable(spec);
//            break;
//        }

//        urlAvailable(spec);
//        urlAvailable(spec3);
    }

    private static void urlAvailable (String spec) {
        try {
            URL url = new URL(spec);
            String file = url.getFile();
            System.out.println("file = " + file);
            String fileName = file.substring(file.lastIndexOf("/") + 1, file.length());
            System.out.println("fileName = " + fileName);

            String host = url.getHost();
            System.out.println("host = " + host);

            int port = url.getPort();
            System.out.println("port = " + port);

            String protocol = url.getProtocol();
            System.out.println("protocol = " + protocol);

            String query = url.getQuery();
            System.out.println("query = " + query);

            URLConnection urlConnection = url.openConnection();
            String contentType = urlConnection.getContentType();
            System.out.println("contentType = " + contentType);

            long contentLengthLong = urlConnection.getContentLengthLong();
            System.out.println("contentLengthLong = " + contentLengthLong);

            download(url, fileName);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void download(URL url, String fileName) {
        try {

            InputStream inputStream = url.openStream();
            int available = inputStream.available();
            System.out.println("available = " + available);
            byte[] message = new byte[1024];
            FileOutputStream fos = new FileOutputStream("E:\\" + fileName);
            int hasRead = 0;
            while ((hasRead = inputStream.read(message)) != -1) {
                fos.write(message, 0, hasRead);
                fos.flush();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
