package com.syh.chapterthirteen;

import java.util.ArrayList;
import java.util.List;

/**
 * @SafeVarargs注释特殊
 */
public class SafeVarargsTest {
    public static void main(String[] args) {
        List list = new ArrayList<Integer>();
        list.add(20);

        List<String> ls = list;
        String s = ls.get(0);
        System.out.println("s = " + s);
    }
}
