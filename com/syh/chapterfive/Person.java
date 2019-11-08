package com.syh.chapterfive;

/**
 * @author HSY
 * @version 1.1
 * 普通人类
 */
public class Person {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    public Person () {}

    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        if (age < 0 || age > 150) {
            throw new Exception("抱歉，您输入的年龄不符合限制");
        } else {
            this.age = age;
        }
    }

    public void go () {
        System.out.println("人走路。。。");
    }
}
