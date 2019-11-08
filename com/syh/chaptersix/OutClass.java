package com.syh.chaptersix;

/**
 * 外部类访问权限只能是public/default
 * 内部类访问权限可以是public/default/protected/private
 */
public class OutClass {

    private static class InnerClass {

    }

    public void sya () {
        class InnerClass {

        }
    }
}
