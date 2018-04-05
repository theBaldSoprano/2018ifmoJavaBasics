package com.study.itmo.gregory.lesson6.solids;

public class Box{

    double volume;

    public Box(double volume) {
        this.volume = volume;
    }

    public boolean canAdd(IsShape shape){
        return volume - shape.getVolume() > 0;
    }

    public double getVolume(){
        return this.volume;
    }

    public void add(IsShape shape){
        if (canAdd(shape))
            volume = volume - shape.getVolume();
    }

}
