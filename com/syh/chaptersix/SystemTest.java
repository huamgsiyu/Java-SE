package com.syh.chaptersix;

import java.util.Map;
import java.util.Properties;

/**
 * 通过System获取环境变量和系统属性
 *  System.getenv()——获取环境变量
 *  System.getProperties()——获取系统属性
 */
public class SystemTest {
    public static void main (String[] args) {
        Map<String, String> map = System.getenv();
        for (String env : map.keySet()) {
            System.out.println("key = " + env);
            System.out.println("value = " + map.get(env));
            System.out.println();
            
        }

        System.out.println("系统属性");
        Properties properties = System.getProperties();
        Object o = properties.get("os.name");
        System.out.println("o = " + o);
        System.out.println("properties.toString() = " + properties.toString());
    }
}
