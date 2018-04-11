package com.study.itmo.gregory.lesson6.solids;

import java.util.ArrayList;

public class Box<T extends Shape>{
    ArrayList<T> shapes;

    public void put(T shape){
        shapes.add(shape);
    }
    public Box() {
        this.shapes = new ArrayList<T>();
    }
    public Double getTotalVolume(){
        double volume = 0;
        for (T shape : shapes){
            volume += shape.getVolume();
        }
        return volume;
    }
}
