package com.study.itmo.gregory.lesson6.currency;

public abstract class Currency{

    private double amount;

    public Currency(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract String format();


}
