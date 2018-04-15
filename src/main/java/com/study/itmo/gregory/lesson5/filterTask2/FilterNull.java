package com.study.itmo.gregory.lesson5.filterTask2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FilterNull implements Filter<Object> {
    @Override
    public boolean apply(Object s) {
        return s != null;
    }
}

class Filters{

    public static <T> T[] filter(T[] array, Filter< ? super T> filter){
        int amount = 0;
        for (T t : array){
            if (!filter.apply(t))amount++;
        }
        T[] temp = Arrays.copyOf(array, amount);
        int ind = 0;
        for (T t : array){
            if (filter.apply(t)){
                temp[ind] = t;
                ind++;
            }
        }
        return temp;
    }
}

class Main{
    public static void main(String[] args) {

    }
}
