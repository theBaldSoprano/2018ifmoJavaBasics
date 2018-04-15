package com.study.itmo.gregory.lesson1;

/** Дан массив чисел.
 * Найдите первое уникальное в этоv массиве число.
 Например, для массива [1, 2, 3, 1, 2, 4] это число 3.
*/

 public class Task7 {

    public static int getFirstUniqueElement(int[] array){

       int current;

        for (int i = 0; i < array.length; i++) {

            current = array[i];
            int count = 0;
            for (int j = 0; j < array.length; j++) {
                if (current == array[j]) count++;
            }
            if (count < 2) return current;

        }

        throw new IllegalArgumentException();
    }

}
