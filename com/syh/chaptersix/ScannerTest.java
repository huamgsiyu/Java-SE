package com.syh.chaptersix;

import java.util.Scanner;

/**
 * 了解Scanner类
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
    }
}
