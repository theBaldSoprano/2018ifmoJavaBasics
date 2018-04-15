package com.study.itmo.gregory.lesson1;

public class Task5 {

    public static int getFibonacci(int n) {

        if (n < 1) throw new IllegalArgumentException();

        int[] array = new int[n];
        array[0] = 0;
        if (n > 1) {
            array[1] = 1;
        }

        for (int i = 2; i < n; i++) {

            array[i] = array[i - 1] + array[i - 2];

        }

        return array[n - 1];


    }

}
