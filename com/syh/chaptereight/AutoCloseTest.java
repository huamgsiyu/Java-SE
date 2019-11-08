package com.syh.chaptereight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AutoCloseTest {
    public static void main(String[] args) {
        try (
                //声明、初始化可关闭的资源
                //try语句会自动关闭这个资源
                BufferedReader bufferedReader = new BufferedReader(new FileReader("a.txt"));
        ) {
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
