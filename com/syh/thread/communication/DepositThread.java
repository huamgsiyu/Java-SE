package com.syh.thread.communication;

public class DepositThread extends Thread{
    private Account account;
    private double amout;

    public DepositThread() {
    }

    public DepositThread(Account account, double amout) {
        this.account = account;
        this.amout = amout;
    }

    @Override
    public void run () {
        account.deposit(amout);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmout() {
        return amout;
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }
}
