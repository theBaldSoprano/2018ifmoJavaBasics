package com.study.itmo.gregory.lesson3;

public class Task1 {

    public static String getMostLongString(String[] array) {

        if (array.length < 1) throw new IllegalArgumentException();

        int length = array[0].length();
        String string = array[0];

        for (int i = 0; i < array.length; i++) {

            if (array[i].length() > length){
                string = array[i];
                length = array[i].length();
            }
        }
        return string;
    }


}
