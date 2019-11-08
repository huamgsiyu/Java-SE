package com.syh.chapterfive;

public class Student {
    /**
     * 1、编写一个学生类，提供name/age/gender/phone/address/email成员变量，
     *  且为每个成员变量提供setter/getter方法。为学生类提供默认的构造器和带所
     *  有成员变量的构造器。为学生类提供方法，用于描绘吃、喝、玩、睡等行为。
     */

    private String name;

    private int age;

    private boolean gender;

    private String phone;

    private String address;

    private String email;

    public String getName() {
        return name;
    }

    public Student () { }

    public Student(String name, int age, boolean gender, String phone, String address, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void eat () {
        System.out.println("我吃。。。");
    }

    public void drink () {
        System.out.println("我喝。。。");
    }

    public void play () {
        System.out.println("我玩。。。");
    }

    public void sleep () {
        System.out.println("我睡。。。");
    }
}
