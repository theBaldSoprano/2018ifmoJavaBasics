package com.study.itmo.gregory.lesson5.filterTask2;

public class FilterNullStrings implements Filter {
    @Override
    public boolean apply(Object s) {
        return s != null;
    }
}
