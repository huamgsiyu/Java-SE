package com.syh.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StringTool {
    public static void main(String[] args) throws IOException {

        //读取文件
        File file = new File("C:\\Users\\Administrator\\Desktop\\pop.txt");
        //文件转为流
        FileInputStream fis = new FileInputStream(file);
        //定义一个byte数组保存值
        byte[] bytes= new byte[1024];
        //定义一个StringBuilder保存值
        StringBuilder message = new StringBuilder(" ");
        while (fis.available() > 0) {
            int read = fis.read(bytes);
            if (read != -1) {
                message.append(new String(bytes));
            }
        }
        //输出值
        String content = message.toString();
        //把斜杠和星号都去掉，去掉多余的空格
        content = content.replaceAll("\\/", "");
        content = content.replaceAll("\\*", "");
        content = content.replaceAll("\\t", "");
        System.out.println(content);
    }
}
