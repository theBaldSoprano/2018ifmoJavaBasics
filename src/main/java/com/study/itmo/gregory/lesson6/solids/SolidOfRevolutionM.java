package com.study.itmo.gregory.lesson6.solids;

public abstract class SolidOfRevolutionM implements Shape {

    protected double radius;

    public double getRadius() {
        return radius;
    }

    public SolidOfRevolutionM(double radius) {
        this.radius = radius;
    }
}
