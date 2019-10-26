package com.syh;

import org.junit.Test;

import java.util.Random;

public class PaintCircle {
    /**
     * 画一个近似圆
     */
    @Test
    public void paintCircle(){
        //定义半径，范围在5-20
        Random random = new Random();
        int radius = 0;
        while (radius < 5){
            radius = random.nextInt(20);
        }
        System.out.println("radius = " + radius);
        //for循环画圆
        for (int i = 0; i <= 2 * radius; i += 2) {
            //计算圆前面的空格数
            int previousBlank = (int)Math.round(radius - Math.sqrt(2 * radius * i - i * i));
            //计算中间的空格，直径减去前后空格就是中间的空格
            int centerBlank = 2 * (radius - previousBlank);

            //打印前面的空格
            print(previousBlank);
            //打印*
            System.out.print("*");
            //打印中间的空格
            print(centerBlank);
            //打印后面的*，并换行
            System.out.println("*");
        }
    }
    private void print(int num){
        while (num >= 0){
            System.out.print(" ");
            num--;
            System.out.println();
        }
    }
}
