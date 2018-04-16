package com.study.itmo.gregory.lesson12;

public class Solution {
    public static void main(String[] args) {
        Double aDouble = new Double(444.5);
        double d = aDouble.doubleValue();
        aDouble = Double.valueOf(d);
        d = Double.parseDouble("4.55");


        for (Integer i = 0; i < 1000; i++) {
            //todo NOTE на каждой итерации будет создан экземпляр
            //значения байтовые интовые -127 до 127 хэшируеются
        }
    }
}
