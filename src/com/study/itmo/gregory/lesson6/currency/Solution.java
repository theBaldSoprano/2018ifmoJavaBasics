package com.study.itmo.gregory.lesson6.currency;

public class Solution {
    public static void main(String[] args) {
        Currency rub = new RUB(500);
        System.out.println(rub.format());
    }
}
