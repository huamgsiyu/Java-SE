package com.syh.thread.communication;

public class Account {
    //账户编号
    private String accountNo;
    //账户余额
    private double balance;

    public Account() {}

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    //取钱
    public synchronized void draw (double drawAmout) {
        //账户余额大于取钱数目
        if (getBalance() >= drawAmout) {
            //吐出钞票

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //修改账户余额
            balance -= drawAmout;
            System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmout + "账户余额为：" + getBalance());
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //存钱
    public synchronized void deposit (double amout) {
        balance += amout;
        System.out.println(Thread.currentThread().getName() + "存钱成功，当前余额：" + balance);
        try {
            notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode () {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == Account.class) {
            Account target = (Account)obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }
}
