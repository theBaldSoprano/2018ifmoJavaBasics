package com.study.itmo.gregory.lesson3;

public class Task4 {



    public static int getWordsAmount(String text){

        String [] words = text.split("[-. ,:!]");

        return words.length;

    }

    public static String[] getWords(String text){

        String [] words = text.split("[-. ,:!]");

        return words;

    }


}
