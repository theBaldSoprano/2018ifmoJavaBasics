package com.study.itmo.gregory.lesson9.needles;

import java.util.Random;

import static com.study.itmo.gregory.lesson9.needles.BuffonsNeedle.getPi;

public class Solution {
    public static void main(String[] args) {

        for (int i = 0; i < 30; i++) {
            System.out.println(getPi(10000));
        }




    }
}
