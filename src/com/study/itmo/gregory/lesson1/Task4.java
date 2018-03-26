package com.study.itmo.gregory.lesson1;

import java.util.ArrayList;

public class Task4 {

    /*Напишите программу, которая вычислит ​простые числа​ в пределах от 2 до 100.
    Для решения этой задачи понадобится вычислить остаток от деления.
    В Java для этого есть оператор % (например, 103 % 10 это 3).*/

    public static void printPrimeNums(){

        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(2);

        myLabel: for (int i = 3; i < 101; i++) {

            for (int j = 2; j < i; j++) {

                if (i % j == 0) continue myLabel;


            }
            arrayList.add(i);

        }
        System.out.println(arrayList);
    }
}
