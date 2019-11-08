package com.syh.chapterseven;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.stream.Stream;

/**
 * HashSet类的了解
 */
public class HashSetTest {
    HashSet<String> hashSet = null;
    @Test
    public void init () {
        hashSet = new HashSet();
        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("3");
        hashSet.add("4");

        hashSet.add("4");
        int hashCode = hashCode();
        System.out.println("hashCode = " + hashCode);

        LinkedHashSet<String> list = new LinkedHashSet<>();
        list.add(null);
        Stream<String> stream = list.stream();
        stream.forEach(obj -> System.out.println("obj = " + obj));
    }

    public int hashCode() {
        int h = 0;
        Iterator<String> i = hashSet.iterator();
        while (i.hasNext()) {
            String obj = i.next();
            if (obj != null){
                h += obj.hashCode();
                System.out.println("obj.hashCode() = " + obj.hashCode());
            }
        }
        return h;
    }
}
