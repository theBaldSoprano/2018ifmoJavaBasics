package com.study.itmo.gregory.lesson1;

public class Task2 {

    public static double getAlgSum(int ammount, double power){

        if (ammount < 0) throw new IllegalArgumentException();

        double sum = 0;

        for (int i = 1; i <= ammount; i++) {

            sum = sum + Math.pow(i, power);

        }

        return sum;

    }


}
