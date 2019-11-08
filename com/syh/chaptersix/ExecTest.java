package com.syh.chaptersix;

import java.io.IOException;

public class ExecTest {
    public static void main(String[] args) throws IOException {
        /**
         * 获取Runtime实例
         */
        Runtime runtime = Runtime.getRuntime();

        runtime.exec("notepad.exe");
    }
}
