package com.syh.thread;

import com.syh.thread.Account;

public class DrawThread extends Thread {
    //模拟用户账户
    private Account account;
    //当前取钱线程希望取的钱数
    private double drawAmout;

    public DrawThread() { }

    public DrawThread(String name, Account account, double drawAmout) {
        super(name);
        this.account = account;
        this.drawAmout = drawAmout;
    }

    @Override
    public void run () {
        account.draw(drawAmout);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getDrawAmout() {
        return drawAmout;
    }

    public void setDrawAmout(double drawAmout) {
        this.drawAmout = drawAmout;
    }
}
