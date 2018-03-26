package com.study.itmo.gregory.lesson1;

public class Task3 {

    /* Заполните массив случайным числами и отсортируйте его.
    Используйте ​сортировку пузырьком​, ​сортировку выбором​ или ​сортировку вставками​. */

    public static void sort (int[] array){

        int min;

        int l = 0;

        int index = 0;

        for (int i = 0; i < array.length - 1; i++) {

            min = array[l];

            for (int j = l; j < array.length; j++) {

                if (min > array[j]){

                    min = array[j];
                    index = j;

                }
            }

            int tmp = array[l];
            array[l] = min;
            array[index] = tmp;


            l++;
        }


    }


}
