package com.syh.chaptersix;

import org.junit.Before;
import org.junit.Test;

/**
 * Lambda表达式
 *  Lambda表达式与匿名内部类的比较
 */
public class CommandTest {
    private ProcessArray processArray = null;
    int[] array = {2, 2, 3, 1};

    @Before
    public void init() {
        processArray = new ProcessArray();
    }

    /**
     * 匿名内部类写法
     */
    @Test
    public void test () {
        processArray.process(new Command() {
            @Override
            public void process(int[] target) {
                int sum = 0;
                for (int i = 0; i < array.length; i++) {
                    sum += array[i];
                }
                System.out.println("sum = " + sum);
            }
        }, array);
    }

    /**
     * Lambda表达式写法
     */
    @Test
    public void test2 () {
        processArray.process((int[] target) -> {
            int sum = 0;
            for (int i = 0; i < target.length; i++) {
                sum += target[i];
            }
            System.out.println("sum = " + sum);
        }, array);
    }
}

class ProcessArray {
    public void process (Command command, int[] target) {
        command.process(target);
    }
}

interface Command {
    void process(int[] target);
}
