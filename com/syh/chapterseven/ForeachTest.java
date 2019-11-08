package com.syh.chapterseven;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * forrach遍历集合元素
 */
public class ForeachTest {
    Collection<String> collection = new ArrayList<>();

    @Before
    public void init () {
        collection.add("1");
        collection.add("2");
        collection.add("3");
        collection.add("4");
    }
    /**
     * foreach
     */
    @Test
    public void foreach () {
        for (String c : collection) {
            System.out.println("c = " + c);
        }
    }

}
