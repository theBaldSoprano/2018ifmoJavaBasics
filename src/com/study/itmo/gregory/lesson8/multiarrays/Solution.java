package com.study.itmo.gregory.lesson8.multiarrays;

import static com.study.itmo.gregory.lesson8.multiarrays.TasksMultiArrays.findMostFreq;
import static com.study.itmo.gregory.lesson8.multiarrays.TasksMultiArrays.isSquare;

public class Solution {

    public static void main(String[] args) {
        char [] arr = {'а','а','a','a','б','б','б','р','р','к'};
        char most = findMostFreq(arr);
        System.out.println(most);

    }

}
