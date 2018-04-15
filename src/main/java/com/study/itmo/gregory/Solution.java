package com.study.itmo.gregory;

import java.util.Date;
import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        Date date1 = new Date();
        long before = date1.getTime();
        Random random = new Random();

        int res = 0;
        byte[] test = new byte [50000000];
        for (int i = 0; i < 50000000; i++) {
            test[i] = (byte)random.nextInt(128);
        }
        for (int i = 0; i < test.length; i++) {
            if (test[i] > 77) res++;
        }
        System.out.println("done " + res);

        Date date2 = new Date();
        long after = date2.getTime();
        long took = after - before;
        System.out.println(took);
        Date date3 = new Date(took);
        System.out.println(date3.getSeconds());

        System.out.println("done " + res);

    }
}
