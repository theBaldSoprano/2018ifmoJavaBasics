package com.study.itmo.gregory.lesson1;

/*Дан массив и число K. Найдите первые K самых часто встречающихся
элементов.*/

import java.util.*;

public class Task8 {

    public static int[] getMostFrequentlyAppeared(int[] array, int n) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {

            int current = array[i];
            int matches = -1;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == current) matches++;
            }
            map.put(current, matches);

        }

        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        int[] result = new int[n];
        int count = 0;

        Iterator<Map.Entry<Integer, Integer>> iterator = sortedMap.entrySet().iterator();

        for (; count < n; count++) {
            result[count] = iterator.next().getKey();
        }

        return result;


    }

    public static int[] getMostFrequentlyAppearedAnotherWay(int[] array, int n) {

        int[] result = new int[array.length];

        int current;
        int last = array[0] + 111;


        for (int i = 0; i < array.length; i++) {
            current = array[i];
            if (current != last) {
                int index = getFirstAppearence(array, current);
                for (int j = 0; j < array.length; j++) {
                    if (array[j] == current) result[index]++;
                }
            }
            last = current;
        }

        //todo доделать

        return result;
    }

    public static int getFirstAppearence(int[] array, int n) {

        int index = -1;

        for (int i = 0; i < array.length; i++) {

            if (array[i] == n) {
                index = i;
                break;
            }
        }
        return index;
    }
}
