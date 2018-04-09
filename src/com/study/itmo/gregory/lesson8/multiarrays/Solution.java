package com.study.itmo.gregory.lesson8.multiarrays;

import static com.study.itmo.gregory.lesson8.multiarrays.TasksMultiArrays.findMostFreq;
import static com.study.itmo.gregory.lesson8.multiarrays.TasksMultiArrays.isSquare;

public class Solution {

    public static void main(String[] args) {
        char [] arr = {'а','а','а','а','б','б','б','р','р','к','з','з','з','л','л','л','л'};
        char most = findMostFreq(arr);
        System.out.println(most);

    }

}
