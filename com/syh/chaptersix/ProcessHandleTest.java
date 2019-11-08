package com.syh.chaptersix;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

/**
 * ProcessHandle类的使用
 */
public class ProcessHandleTest {
    public static void main(String[] args) throws IOException {
        /**
         * 获取Runtime实例
         */
        Runtime runtime = Runtime.getRuntime();

        /**
         * 运行记事本程序
         */
        Process nodePad = runtime.exec("notepad.exe");

        ProcessHandle processHandle = nodePad.toHandle();

        /**
         * 进程是否运行
         */
        boolean alive = processHandle.isAlive();
        System.out.println("alive = " + alive);

        /**
         * 进程ID
         */
        long pid = processHandle.pid();
        System.out.println("pid = " + pid);

        /**
         * 父进程ID
         */
        Optional<ProcessHandle> parent = processHandle.parent();
        System.out.println("parent = " + parent);

        /**
         * ProcessHandle.Info信息获取进程相关信息
         */
        ProcessHandle.Info info = processHandle.info();
        System.out.println("info = " + info);

        /**
         * 进程命令
         */
        Optional<String> command = info.command();
        System.out.println("command = " + command);

        /**
         * 进程启动时间
         */
        Optional<Instant> instant = info.startInstant();
        System.out.println("instant = " + instant);
    }
}
