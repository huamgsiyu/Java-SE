package com.syh.thread;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    //账户编号
    private String accountNo;
    //账户余额
    private double balance;

    private final ReentrantLock lock = new ReentrantLock();
    public Account() {}

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public void draw (double drawAmout) {
        lock.lock();
        try {
            //账户余额大于取钱数目
            if (getBalance() > drawAmout) {
                //吐出钞票
                System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票：" + drawAmout);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //修改账户余额
                setBalance(getBalance() - drawAmout);
                System.out.println("账户余额为：" + getBalance());
            } else {
                System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
            }
        } finally {
            lock.unlock();
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

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
