package com.study.itmo.gregory.lesson1;

import java.io.IOException;
import java.util.Arrays;

import static com.study.itmo.gregory.lesson1.Task8.getMostFrequentlyAppeared;
import static com.study.itmo.gregory.lesson1.Task8.getMostFrequentlyAppearedAnotherWay;


public class Solution {

    public static void main(String[] args) throws IOException {

        int[] array = {5, 66, 6, 3333, 5, 3, 5, 5, 3333, 3333, 66};


        int[] result = getMostFrequentlyAppearedAnotherWay(array, 8);

        System.out.println(Arrays.toString(result));









    }

}
