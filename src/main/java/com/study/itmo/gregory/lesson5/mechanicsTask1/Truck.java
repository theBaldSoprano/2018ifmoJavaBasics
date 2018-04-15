package com.study.itmo.gregory.lesson5.mechanicsTask1;

public class Truck extends Automobile {

    protected Truck(Engine engine) {
        super(engine);
    }

    @Override
    public Engine getEngine() {
        return this.engine;
    }
}
