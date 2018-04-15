package com.study.itmo.gregory.lesson1;

/*Дан массив и число K. Найдите первые K самых часто встречающихся
элементов.*/

import java.util.*;

public class Task8 {

    public static int[] getMostFrequentlyAppearedByMap(int[] array, int n) {

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

    public static int[] getMostFrequentlyAppearedByLoops(int[] array, int n) {

        int[] temp = new int[array.length];
        int current;
        for (int i = 0; i < array.length; i++) {
            current = array[i];
            if (i == getFirstAppearance(array, current)) {
                int index = getFirstAppearance(array, current);
                for (int j = 0; j < array.length; j++) {
                    if (array[j] == current) temp[index]++;
                }
            }
        }
        int elementsAmount = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != 0) elementsAmount++;
        }
        if (n > elementsAmount) throw new IllegalArgumentException();
        int[] result = new int[n];
        for (int i = 0; i < result.length; i++) {
            int indexOfMax = getMaxIndex(temp);
            result[i] = array[indexOfMax];
            temp[indexOfMax] = 0;
        }
        return result;
    }

    public static int getFirstAppearance(int[] array, int n) {

        int index = -1;

        for (int i = 0; i < array.length; i++) {

            if (array[i] == n) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int getMaxIndex(int[] array) {
        int index = 0;
        int compare = array[0];

        for (int i = 0; i < array.length; i++) {
            if (compare < array[i]) {
                compare = array[i];
                index = i;
            }
        }
        return index;
    }
}
