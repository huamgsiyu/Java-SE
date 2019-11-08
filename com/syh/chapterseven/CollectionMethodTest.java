package com.syh.chapterseven;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合Collection接口方法的测试
 */
public class CollectionMethodTest {
    Collection<String> collection = new ArrayList<>();

    /**
     * collection集合初始化
     */
    @Before
    public void init () {
        collection.add("我");
        collection.add("是");
        collection.add("H");
        collection.add("S");
        collection.add("Y");
    }

    /**
     * add(Object o)
     */
    @Test
    public void add () {
        collection.add("我是HHH");
        iterator(collection);
    }

    /**
     * addAll(Collection c)
     */
    @Test
    public void addAll () {
        Collection<String> c = new ArrayList<>();
        c.add("我");
        c.add("是");
        c.add("H");
        c.add("Q");
        c.add("H");
        collection.addAll(c);
        iterator(collection);
    }
    /**
     * clear()
     */
    @Test
    public void clear () {
        collection.clear();
        iterator(collection);
    }
    /**
     * contains(Object o)
     */
    @Test
    public void contains () {
        boolean contains = collection.contains("我");
        System.out.println("contains = " + contains);
    }

    /**
     * containsAll(Collection c)
     */
    @Test
    public void containsAll () {
        Collection<String> c = new ArrayList();
        c.add("我");
        c.add("是");
        boolean containsAll = collection.containsAll(c);
        System.out.println("containsAll = " + containsAll);
    }

    /**
     * isEmpty()
     */
    @Test
    public void isEmpty () {
        //collection.clear();
        boolean empty = collection.isEmpty();
        System.out.println("empty = " + empty);
    }
    /**
     * iterator()
     */
    public static void iterator (Collection c) {
        Iterator iterator = c.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println("next = " + next);
        }
    }
    /**
     * remove(Object o)
     */
    @Test
    public void remove () {
        collection.remove("我");
        iterator(collection);
    }

    /**
     * removeAll(Collection c)
     */
    @Test
    public void removeAll () {
        Collection<String> c = new ArrayList<>();
        c.add("我");
        c.add("是");
        c.add("G");
        boolean removeResult = collection.removeAll(c);
        System.out.println("removeResult = " + removeResult);
        iterator(collection);

    }
    /**
     * size()
     */
    @Test
    public void size () {
        int size = collection.size();
        System.out.println("size = " + size);
    }
    /**
     * toArray()
     */
    @Test
    public void toArray () {
        Object[] objects = collection.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println("objects[i] = " + objects[i]);
        }
    }

    /**
     * removeIf(Prodicate filter)
     */
    @Test
    public void removeIf () {
        collection.removeIf(element -> element.equals("H"));
//        collection.removeIf(element -> element.length() < 2);
        iterator(collection);
    }
}
