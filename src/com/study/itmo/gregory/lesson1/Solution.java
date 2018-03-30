package com.study.itmo.gregory.lesson1;

import java.io.IOException;
import java.util.Arrays;

import static com.study.itmo.gregory.lesson1.Task8.getMostFrequentlyAppearedByLoops;


public class Solution {

    public static void main(String[] args) throws IOException {

        int[] array = {5,5,5,5,5,4,4,4,4,4,4,4,4,3,3,3,2,2,1};


        int[] result = getMostFrequentlyAppearedByLoops(array, 5);

        System.out.println(Arrays.toString(result));


    }

}
