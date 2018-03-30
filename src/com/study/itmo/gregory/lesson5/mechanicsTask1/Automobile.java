package com.study.itmo.gregory.lesson5.mechanicsTask1;

public abstract class Automobile implements HasEngine{

    protected Engine engine;

    protected Automobile(Engine engine){
        this.engine = engine;
    }
}
