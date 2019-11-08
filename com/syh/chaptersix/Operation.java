package com.syh.chaptersix;

public enum Operation {
    PLUS {
        public double eval (double x, double y) {
            return x + y;
        }
    },
    MINUS {
        public double eval (double x, double y) {
            return x - y;
        }
    },
    TIMES {
        public double eval (double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        public double eval (double x, double y) {
            return x / y;
        }
    };

    public abstract double eval (double x, double y);

    public static void main(String[] args) {
        Operation divide = Operation.valueOf("DIVIDE");
        System.out.println(divide.eval(2, 8));
    }
}
