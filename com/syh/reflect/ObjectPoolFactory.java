package com.syh.reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 实现简单的对象池，该对象池会根据配置文件读取key-value对，然后创建对象，
 * 并将这些对象放入一个HashMap中
 */
public class ObjectPoolFactory {
    private Map<String, Object> objPool = new HashMap<>();

    private Object createObject (String clazzName) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        //根据全限定类名获得Class对象
        Class<?> clazz = Class.forName(clazzName);
        //获取Class对象的默认构造器,根据默认构造器创建实例
        return clazz.getConstructor().newInstance();
    }

    public void initPool (String fileName) {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                ) {
            Properties props = new Properties();
            props.load(fis);
            for (String name : props.stringPropertyNames()) {
                //每取出一对key-value对，就根据value创建一个对象
                //调用createObject()创建对象，并将对象添加到对象池中
                objPool.put(name, createObject(props.getProperty(name)));
            }
        } catch (IOException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException |
                IllegalAccessException |
                ClassNotFoundException e) {
            System.out.println("读取" + fileName + "异常");
        }
    }

    public Object getObject (String name) {
        return objPool.get(name);
    }

    public static void main(String[] args) {
        ObjectPoolFactory pf = new ObjectPoolFactory();
        pf.initPool("obj.txt");
        System.out.println(pf.getObject("a"));
        System.out.println(pf.getObject("b"));
    }
}
