package com.study.itmo.gregory.lesson4;

public class Solution {

    private static int fact(int n) {

        if (n == 1)
            return 1;

        int result = fact(n - 1) * n;

        return result;


    }

    public static void main(String[] args) {

        int foo = 0;
        foo = fact(5); // debug this
    }
}
