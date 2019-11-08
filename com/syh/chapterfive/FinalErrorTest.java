package com.syh.chapterfive;

public class FinalErrorTest {
    final int age;
    {
        //下面这句会出现编译错误，因为age没有进行初始化就已经访问
        // System.out.println("未初始化前访问age：" + age);
        printAge();
        age = 6;
        System.out.println("初始化age后访问：" + age);
    }

    public void printAge () {
        System.out.println("age = " + age);
    }
}
