package com.study.itmo.gregory.lesson6.solids;

import org.jetbrains.annotations.NotNull;

public interface IsShape extends Comparable<IsShape> {

    @Override
    default int compareTo(@NotNull IsShape o) {

        if (this.getVolume() < o.getVolume()) return -1;
        if (this.getVolume() == o.getVolume()) return 0;
        else return 1;

    }

    double getVolume();
}

/*
if (this.getVolume() < shape.getVolume()) return -1;
        if (this.getVolume() == shape.getVolume()) return 0;
        if (this.getVolume() > shape.getVolume()) return 1;
 */