package com.study.itmo.gregory.lesson7;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class ComparatorByLength implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {

        if (o1.length() < o2.length()) return -1;
        else if (o1.length() == o2.length()) return 0;
        else return 1;

    }
}
