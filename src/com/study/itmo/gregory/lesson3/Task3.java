package com.study.itmo.gregory.lesson3;

public class Task3 {

    public static String deleteMatches(String text){

        String match = "бяка";
        String replacement = "[вырезано цензурой]";

        return text.replaceAll(match, replacement);

    }
}
