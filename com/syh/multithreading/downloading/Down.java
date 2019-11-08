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

    public static void main(String[] args) throws IOException, InterruptedException {

        String spec1 = "https://repo1.maven.org/maven2/org/apache/xmlgraphics/xmlgraphics-commons/2.4/xmlgraphics-commons-2.4.jar";
        String spec = "https://www.cnblogs.com/fwnboke/p/8529492.html";
        String spec2 = "http://b-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.jpeg";
        String spec3 = "http://192.168.190.130:8080/download/aaaaa.cfg";

        //根据url，保存地址和文件名把文件下载到本地
        DownUtil downUtil = new DownUtil(spec2, "E:\\download\\20120830173930_PBfJE.jpeg", 20);
        downUtil.download();
        Thread.sleep(200);
        double completeRate = downUtil.getCompleteRate();
        System.out.println("completeRate ==================================== " + completeRate);
    }
}
