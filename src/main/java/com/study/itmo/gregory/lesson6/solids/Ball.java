package com.study.itmo.gregory.lesson6.solids;

public class Ball extends SolidOfRevolutionM {

    public Ball(double radius) {
        super(radius);
    }

    @Override
    public double getVolume() {
        return (4.0/3) * Math.PI * Math.pow(radius, 3);
    }
}
