package com.study.itmo.gregory.lesson6.currency;

public class RUB extends Currency{

    final static public char RUB = '\u20BD';

    public RUB(double amount) {
        super(amount);
    }


    @Override
    public String format() {
        return String.format("%f%s",super.getAmount(), RUB);
    }
}
