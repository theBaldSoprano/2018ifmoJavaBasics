package com.study.itmo.gregory.lesson10;

import java.util.Arrays;

public class GetMax {
    public <T extends Comparable<? super T>> T getMax(T[] array) {
        T max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) max = array[i];
        }
        return max;
    }

    public static void main(String[] args) {

    }
}
