package com.syh.reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ExtendedObjectPoolFactory {

    //对象池
    private Map<String, Object> objPool = new HashMap<>();
    //属性类
    private Properties config = new Properties();
    //主方法
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        /**
         * exeObj.txt的内容为
         *      a=javax.swing.JFrame
         *      b=javax.swing.JLabel
         *      #set the title of a
         *      a%title=Test Title
         */
        ExtendedObjectPoolFactory epf = new ExtendedObjectPoolFactory();
        epf.init("exeObj.txt");
        epf.initPool();;
        epf.initProperty();
        System.out.println(epf.getObj("a"));
    }

    //从指定属性文件中初始化Properties对象
    public void init (String fileName) {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                ){
            config.load(fis);
        } catch (IOException e) {
            System.out.println("读取" + fileName + "异常");
        }
    }

    //定义创建对象的方法
    private Object createObj (String clazzName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.getConstructor().newInstance();
    }

    //盖房根据指定文件来初始化对象池
    public void initPool () throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (String name : config.stringPropertyNames()) {
            /*
                每取出一个key-value对，如果key中不包含百分号
                这就表明是根据value来创建一个对象
                调用createObj创建对选哪个，并将对象添加到对象池中
             */
            if (!name.contains("%")) {
                objPool.put(name, createObj(config.getProperty(name)));
            }
        }
    }

    //该方法将会根据属性文件来调用指定对象的setter方法
    public void initProperty () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String name : config.stringPropertyNames()) {
            /*
                每取出一个key-value对，如果key中不包含百分号
                即可认为该key用于控制调用对象的setter方法设置值
                %前半为对象名称，后半控制setter方法名
             */
            if (name.contains("%")) {
                //将配置文件中的key按%分割
                String[] objAndProp = name.split("%");
                //取出调用setter方法的参数值
                Object target = getObj(objAndProp[0]);
                //获取setter方法名：set + "首字母大写" + 剩下部分
                String mtdName = "set" + objAndProp[1].substring(1);
                //通过target的getClass()获取它的实现类所对应的Class对象
                Class<?> targetClass = target.getClass();
                //获取希望调用的setter方法
                Method mtd = targetClass.getMethod(mtdName, String.class);
                //通过Method的invoke方法执行setter方法
                //将config.getProperty(name)的值作为调用setter方法的参数
                mtd.invoke(target, config.getProperty(name));

            }
        }
    }
    public Object getObj (String name) {
        return objPool.get(name);
    }
}
