package com.study.itmo.gregory.lesson5.filterTask2;

import java.util.Comparator;

public class ComparatorBySize implements Comparator<CharSequence>{


    @Override
    public int compare(CharSequence s1, CharSequence s2) {
        int size1 = s1.length();
        int size2 = s2.length();

        if (size1 == size2) return 0;
        else if (size1 < size2) return -1;
        else return 1;
    }
}
