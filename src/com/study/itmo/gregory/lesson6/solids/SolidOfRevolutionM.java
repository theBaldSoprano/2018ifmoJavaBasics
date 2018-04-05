package com.study.itmo.gregory.lesson6.solids;

public abstract class SolidOfRevolutionM implements IsShape {

    protected double radius;

    public double getRadius() {
        return radius;
    }

    public SolidOfRevolutionM(double radius) {
        this.radius = radius;
    }
}
