package com.syh.chaptersix;

public class EnumTest {
    public static void main(String[] args) {
        EnumFirst sumer = EnumFirst.valueOf("SUMER");
        Object obj = sumer;
        System.out.println("obj = " + obj);
    }
}
enum EnumFirst {
    SUMER;
}
