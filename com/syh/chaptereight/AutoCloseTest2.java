package com.syh.chaptereight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * java 9 自动关闭资源的try语句
 */
public class AutoCloseTest2 {
    public static void main(String[] args) throws Exception {
        final BufferedReader bufferedReader = new BufferedReader(new FileReader("a.txt"));

        try (bufferedReader;) {
            System.out.println("bufferedReader = " + bufferedReader);
        }
    }
}
