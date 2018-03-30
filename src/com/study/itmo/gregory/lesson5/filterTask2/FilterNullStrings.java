package com.study.itmo.gregory.lesson5.filterTask2;

public class FilterNullStrings extends Filter {
    @Override
    public boolean apply(String s) {
        return s != null;
    }
}
