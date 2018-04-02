package com.study.itmo.gregory.lesson6.solids;

public class Cylinder extends SolidOfRevolutionM{

    double height;

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    @Override
    public double getVolume() {
        return Math.PI * height * Math.pow(radius, 2);
    }
}
