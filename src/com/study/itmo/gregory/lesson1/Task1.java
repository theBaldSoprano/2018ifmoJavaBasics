package com.study.itmo.gregory.lesson1;

import java.util.Random;

public class Task1 {

    public static int[] getRandomArray(int size){

        if (size < 0) throw new IllegalArgumentException();

        int [] array = new int [size];

        Random ran = new Random();

        for (int i = 0; i < size; i++) {

            array[i] = ran.nextInt(size);

        }

        return array;
    }

    public static int getMax(int[] array){

        if (array.length == 0) throw new IllegalArgumentException();

        int max = array[0];

        for (int i = 0; i < array.length; i++) {

            if (max < array[i]) max = array[i];

        }

        return max;

    }

    public static int getMin(int[] array){

        if (array.length == 0) throw new IllegalArgumentException();

        int min = array[0];

        for (int i = 0; i < array.length; i++) {

            if (min > array[i]) min = array[i];

        }

        return min;

    }

    public static double getMiddle(int[] array){

        if (array.length == 0) throw new IllegalArgumentException();

        int sum = 0;

        for (int i = 0; i < array.length; i++) {

            sum += array[i];

        }



        return sum/array.length;

    }

    public static void printArray(int [] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }




}
