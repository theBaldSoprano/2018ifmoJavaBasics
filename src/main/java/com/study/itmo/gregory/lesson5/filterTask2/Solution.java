package com.study.itmo.gregory.lesson5.filterTask2;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static String[] filter(String[] list, Filter filter){
        //todo отфильтровать согласно разным реализациям фильтра
        //убрать пустые. убрать конкретные (в конструкторе
        int matches = 0;
        for(String s: list){
            if (filter.apply(s)) matches++;
        }
        String [] result = new String[matches];
        int index = 0;
        for (String s : list){
            if (filter.apply(s)){
                result[index] = s;
                index++;
            }
        }
        return result;
    }
    public static String getMax(String[] list, Comparator comparator){
        //todo
        String max = list[0];
        for (String s: list){

            if (comparator.compare(max, s) == 1){

            }

        }

        return null;
    }

    public static void main(String[] args) {
        String[] list = {"foo", null, "bar"};
        System.out.println(Arrays.toString(list));

        Filter filter = new FilterNull();
        list = filter(list, filter);
        System.out.println(Arrays.toString(list));

    }
}
