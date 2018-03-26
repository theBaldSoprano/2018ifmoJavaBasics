package com.study.itmo.gregory.lesson1;

public class Task6 {

    /*Дан массив чисел и число.
    Удалите все вхождения числа в массив (пропусков быть не должно).*/

    public static int[] deleteNFromArray(int[] array, int n){

        int matches = hasMatches(array, n);
        if (matches == 0) return array;
        for (int i = 0; i < matches; i++) {
            array = deleteFirstN(array, n);
        }
        return array;
    }

    public static int[] deleteFirstN(int[] array, int n){

        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == n){
                index = i;
                break;
            }
        }

        int[] first = new int[index];
        for (int i = 0; i < index; i++) {
            first[i] = array[i];
        }

        int[] second = new int[array.length - index -1];
        for (int i = index + 1; i < array.length; i++) {
            second[i - index - 1] = array[index + 1];
        }

        int resultSize = first.length + second.length;
        int[] result = new int[resultSize];

        for (int i = 0; i < first.length; i++) {
            result[i] = first[i];
        }
        for (int i = 0; i < second.length; i++) {
            result[first.length] = second[i];
        }

        return result;

    }

    public static int hasMatches(int[] array, int n){

        int amount = 0;

        for (int i = 0; i < array.length; i++) {

            if (array[i] == n) amount++;

        }
        return amount;
    }

}
