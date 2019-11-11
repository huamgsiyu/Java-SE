package com.syh.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class FieldTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Person person = new Person();

        Class<Person> clazz = Person.class;
        Field[] fields = clazz.getDeclaredFields();
        Object name = null;
        int age = 0;
        for (Field field : fields) {
            //取消访问权限检查
            field.setAccessible(true);
            if (field.getName().equals("name")) {
                field.set(person, "HSY");
            } else {
                field.setInt(person, 24);
            }
        }
        System.out.println("person = " + person.toString());
    }
}
