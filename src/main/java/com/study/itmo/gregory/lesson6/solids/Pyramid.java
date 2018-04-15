package com.study.itmo.gregory.lesson6.solids;

public class Pyramid {

    double height;
    double baseArea;

    public Pyramid(double height, double baseArea) {
        this.height = height;
        this.baseArea = baseArea;
    }

    //@Override
    public double getVolume() {
        return (baseArea * height) / (1.0/3.0);
    }
}
