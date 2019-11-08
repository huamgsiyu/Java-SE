package com.syh.chapterthreehomework;

import org.junit.Test;

public class Evaluation {

    /**
     * 例如：已知有一个数列：f(0) = 1, f(1) = 4, f(n + 2) = 2 * f(n+1) + f(n),其中n是大于0的整数，求f(10)的值。
     */
    @Test
    public void solveProblems () {
        int num = 10;
        int sum = solve(num);

        System.out.println("sum = " + sum);
    }

    public int solve (int num) {
        int f0 = 1;
        int f1 = 4;
        int number = 0;
        if (num == 0) {
            return f0;
        } else if (num == 1) {
            return  f1;
        } else {
            return 2 * (solve(num - 1) + solve(num - 2));
        }
    }

    /**
     * 例如：已知有一个数列：f(21) = 1, f(20) = 4, f(n + 2) = 2 * f(n+1) + f(n),其中n是大于0的整数，求f(10)的值。
     *  f(n + 2) = 2 * f(n + 1) + f(n)  ->  f(n) = f(n + 2) - 2 * f(n + 1);
     */
    @Test
    public void solveTwoProblems () {
        System.out.println("num = " + solveTwo(10));;

    }

    public int solveTwo (int num) {
        if (num == 21) {
            return 1;
        } else if (num == 20) {
            return 4;
        } else {
            return solveTwo(num + 2) - 2 * solveTwo(num + 1);
        }
    }
}
