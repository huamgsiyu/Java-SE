package com.syh.chapterthreehomework;

public class ClassExtendsTest {
    public static void main(String[] args) {
        new Leaf();
        System.out.println();
        new Leaf();
    }
}
class Root {
    static {
        System.out.println("Root的静态初始化块" );
    }
    {
        System.out.println("Root的普通初始化块");
    }
    public Root () {
        System.out.println("Root的无参构造器");
    }
}
class Mid extends Root {
    static {
        System.out.println("Mid的静态初始化块");
    }
    {
        System.out.println("mid的普通初始化块");
    }
    public Mid () {
        System.out.println("Mid的无参构造器");
    }
    public Mid (String name) {
        this();
        System.out.println("mid的有参构造器" + name);
    }
}
class Leaf extends Mid {
    static {
        System.out.println("Leaf的静态初始化块");
    }
    {
        System.out.println("Leaf的普通初始化块");
    }
    public Leaf () {
        super("疯狂Java讲义");
        System.out.println("Leaf的无参构造器");
    }
}

