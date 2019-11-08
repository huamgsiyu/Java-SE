package com.syh.chaptereight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * finally块的测试
 */
public class FinallyTest {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("a.txt");

        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
//            return还是会执行finally里的代码块
//            return;

//            退出虚拟机，将不再执行finally里的代码
            System.exit(1);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("执行finally块里的资源回收");
        }
    }
}
