package com.study.itmo.gregory.lesson5;

import org.jetbrains.annotations.NotNull;

public class Shape implements Comparable<Shape>{

    protected double area;

    public Shape(double area) {
        this.area = area;
    }

    @Override
    public int compareTo(Shape o) {
        if (this.area < o.area) return -1;
        else if (this.area == o.area) return 0;
        else return 1;
    }
}

class Circle extends Shape {

    protected double radius;

    public Circle(double radius) {
        super(Math.PI * radius * radius);
        this.radius = radius;
    }


    public void foo() {

    }
}

