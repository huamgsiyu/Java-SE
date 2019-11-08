package com.syh.chaptersix;

class Person {
    private final int age;
    public String name;

    public Person () {
        age = 5;
        //age进行初始化后，不能再修改age的值
        //age = 10;
    }

    public Person (int age) {
        this.age = age;
        //age进行初始化后，不能再修改age的值
        //age = 10;
    }

}
public class PersonTest {
    public static void main (String[] args) {
        final Person person = new Person();
        person.name = "HSY";
        System.out.println(person.name);
    }
}
