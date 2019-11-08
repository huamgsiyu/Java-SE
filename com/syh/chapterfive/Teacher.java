package com.syh.chapterfive;

/**
 * @author HSY
 * @Version 1.1
 * 教师类
 */
public class Teacher extends Person{

    /**
     * 教师编号
     */
    private String tno;

    public Teacher () {}

    public Teacher (String tno) {
        this.tno = tno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public void teach () {
        System.out.println("老师教授知识。。。");
    }
}
