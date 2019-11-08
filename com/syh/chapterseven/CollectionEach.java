package com.syh.chapterseven;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionEach {
    
    
    @Test
    public void forEach () {
        Collection<String> collection = new ArrayList<>();
        collection.add("1");
        collection.add("2");
        collection.add("3");
        collection.add("4");
        
        collection.forEach( obj -> System.out.println("obj = " + obj));
    }
    
}
