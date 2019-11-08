package com.syh.chaptereight;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add(1);

        list.stream().forEach(obj -> System.out.println("obj = " + obj.getClass()));
    }
}
