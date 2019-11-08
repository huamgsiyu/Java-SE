package com.syh.chapterfive;

/**
 * @author HSY
 * @version 1.1
 * 学生类
 */
public class StudentNew extends Person {

    /**
     * 学号
     */
    private String sno;

    /**
     * 班级
     */
    private String className;

    public StudentNew () {}

    public StudentNew (String sno, String className) {
        this.sno = sno;
        this.className = className;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void go () {
        System.out.println("学生走路。。。");
    }

    public void study () {
        System.out.println("学生学习。。。");
    }
}
