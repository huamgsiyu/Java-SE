package com.syh;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ChapterFourHomework {
    Map<Integer, String> moneyName = new HashMap<>();
    Map<Integer, String> moneyUnit = new HashMap<>();
    Map<Integer, String> moneyDecimals = new HashMap<>();

    /**
     * 题目：
     *  把浮点数转换为人民币读法字符串，如将1006.333转换为壹仟零陆元叁角叁分
     */
    @Test
    public void test() {
        double money = 243141.43;
        //分为整数部分和小数部分，小数部分只保留2位
        initMoney();
        String moneryStr = String.valueOf(money);
        System.out.println("moneryStr = " + moneryStr);
        String[] split = moneryStr.split("\\.");
        System.out.println("split.length = " + split.length);
        String intMoney = split[0];
        String decimalsMoney = split[1];
        System.out.println("decimalsMoney = " + decimalsMoney);
        char[] intChars = intMoney.toCharArray();

        char[] decimalsChars = decimalsMoney.toCharArray();
        //保存人民币叫法
        StringBuilder stringBuilder = new StringBuilder("");
        //整数部分叫法
        for (int i = 0; i < intChars.length; i++){
            if(!String.valueOf(intChars[i]).equals("0")) {
                int num = intChars.length - 1 - i;
                int unit = 1;
                while (num > 0) {
                    unit *= 10;
                    num--;
                }
                String name = moneyName.get(Integer.parseInt(String.valueOf(intChars[i])));
                String unitM = moneyUnit.get(unit);
                if (name != null) {
                    stringBuilder = stringBuilder.append(name);
                }
                if (unitM != null) {
                    stringBuilder = stringBuilder.append(unitM);
                }
            }else {
                if(!(stringBuilder.lastIndexOf("零") == stringBuilder.length() - 1)){
                    stringBuilder = stringBuilder.append("零");
                }

            }
        }
        //如果100.77，这种数组，整数部分的零要去掉。
        int index = stringBuilder.lastIndexOf("零");
        if (stringBuilder.lastIndexOf("零") == stringBuilder.length() - 1) {
            stringBuilder.delete(index, index + 1);
        }
        stringBuilder = stringBuilder.append("元");

        //小数部分叫法
        for (int i = 0; i < decimalsChars.length; i++) {
            //小数部分只计算到分
            if(i == 2){
                break;
            }

            String unitM = moneyDecimals.get(i);
            String name = moneyName.get(Integer.parseInt(String.valueOf(decimalsChars[i])));
            System.out.println("decimalsChars = " + decimalsChars[i]);
            System.out.println("name = " + name);
            if (name != null) {
                stringBuilder = stringBuilder.append(name);
            }
            if (unitM != null) {
                stringBuilder = stringBuilder.append(unitM);
            }
        }
        System.out.println("stringBuilder = " + stringBuilder);
    }

    private void initMoney(){
        moneyUnit.put(0, "零");
        moneyUnit.put(10, "拾");
        moneyUnit.put(100, "佰");
        moneyUnit.put(1000, "仟");
        moneyUnit.put(10000, "萬");
        moneyUnit.put(100000, "拾萬");
        moneyUnit.put(1000000, "佰萬");
        moneyUnit.put(10000000, "千萬");
        moneyUnit.put(100000000, "億");

        moneyName.put(1, "壹");
        moneyName.put(2, "贰");
        moneyName.put(3, "叁");
        moneyName.put(4, "肆");
        moneyName.put(5, "伍");
        moneyName.put(6, "陆");
        moneyName.put(7, "柒");
        moneyName.put(8, "捌");
        moneyName.put(9, "玖");

        moneyDecimals.put(0, "角");
        moneyDecimals.put(1, "分");

    }
}
