package com.syh.chapterseven;

import java.util.Arrays;
import java.util.List;

/**
 * 固定长度的List——Arrays.ArrayList
 */
public class FixedSizeList {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("222", "333", "444");
        strings.stream().forEach(obj -> System.out.println("obj = " + obj));

        boolean add = strings.add("55555");
        System.out.println("add = " + add);
        boolean remove = strings.remove("222");
        System.out.println("remove = " + remove);
    }
}
