package com.syh.chapterthirteen;

public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            System.out.println("i = " + i);
            Thread.sleep(1000);
        }
    }
}
