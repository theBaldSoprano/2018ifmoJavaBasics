package com.study.itmo.gregory.lesson1;

import java.io.IOException;

import static com.study.itmo.gregory.lesson1.Task8.getMostFrequentlyAppeared;


public class Solution {

    public static void main(String[] args) throws IOException {

        int[] array = {5, 66, 6, 3333, 5, 3, 5, 5, 3333, 3333, 66};

        int n = 2;

        int[] result = getMostFrequentlyAppeared(array, n);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }










    }

}
