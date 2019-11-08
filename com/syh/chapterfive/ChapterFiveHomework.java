package com.syh.chapterfive;

import org.junit.Test;

/**
 * 疯狂Java讲义第五章练习题
 */
public class ChapterFiveHomework {

    /**
     * 1、编写一个学生类，提供name/age/gender/phone/address/email成员变量，
     *  且为每个成员变量提供setter/getter方法。为学生类提供默认的构造器和带所
     *  有成员变量的构造器。为学生类提供方法，用于描绘吃、喝、玩、睡等行为。
     */
    @Test
    public void test () {

    }

    /**
     * 2、利用第一题定义的student类，定义一个Student[]数组保存多个Student对
     * 象作为通讯录。程序可通过name/email/address查询，如果找不到数据，则进
     * 行友好提示
     */
    @Test
    public void test1 () {
        Student[] students = new Student[3];
        initStudentMessage(students);

        //根据name查找
        Student studentByName = searchByName("HSY", students);
        if (studentByName != null) {
            System.out.println("找到学生");
        } else {
            System.out.println("抱歉，根据您提供的姓名，无法找到对应的学生");
        }

        //根据email查找
        Student studentByEmail = searchByEmail("80909010@qq.com", students);
        if (studentByEmail != null) {
            System.out.println("找到学生");
        } else {
            System.out.println("抱歉，根据您提供的邮箱，无法找到对应的学生");
        }
        //根据address查找
        Student studentByAddress = searchByAddress("广州市白云区", students);
        if (studentByAddress != null) {
            System.out.println("找到学生");
        } else {
            System.out.println("抱歉，根据您提供的地址，无法找到对应的学生");
        }
    }
    private Student searchByName (String name, Student[] students) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getName().equals(name)) {
                return students[i];
            }
        }
        return null;
    }

    private Student searchByEmail (String email, Student[] students) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getEmail().equals(email)) {
                return students[i];
            }
        }
        return null;
    }

    private Student searchByAddress (String address, Student[] students) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getAddress().equals(address)) {
                return students[i];
            }
        }
        return null;
    }
    private void initStudentMessage (Student[] students) {
        Student studentHSY = new Student("HSY",
                18,
                true,
                "18577361111",
                "广州市天河区",
                "9090900@qq.com");

        Student studentHHH = new Student("HHH",
                18,
                true,
                "18577361112",
                "广州市白云区",
                "8090900@qq.com");

        Student studentCCC = new Student("CCC",
                18,
                true,
                "18577361133",
                "广州市增城区",
                "cc090900@qq.com");

        students[0] = studentHSY;
        students[1] = studentHHH;
        students[2] = studentCCC;
    }

    /**
     * 3、定义普通人、老师、班主任、学生、学校这些类，提供适当的成员变量、方法用于描
     * 述其内部数据和行为特征，并提供主类使之运行。要求有良好的封装性，将不同类放在不同的包
     * 下面，增加文档注释，生成API文档。
     */
    @Test
    public void test2 () {
        //1、人走路
        Person person = new Person();
        person.go();

        //2、学生走路
        Person personStudent = new StudentNew();
        personStudent.go();

        //3、老师走路
        Person personTeacher = new Teacher();
        personTeacher.go();

        //4、学生学习
        StudentNew student = new StudentNew();
        student.study();

        //5、老师教授课程
        Teacher teacher = new Teacher();
        teacher.teach();
    }
}
