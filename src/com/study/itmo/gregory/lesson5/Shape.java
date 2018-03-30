package com.study.itmo.gregory.lesson5;

public class Shape {
    protected double area;



    public Shape(double area) {
        this.area = area;
    }

    private void foo(){

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

