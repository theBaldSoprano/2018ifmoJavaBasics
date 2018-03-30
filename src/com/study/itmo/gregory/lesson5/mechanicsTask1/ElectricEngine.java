package com.study.itmo.gregory.lesson5.mechanicsTask1;

public class ElectricEngine extends Engine {

    public ElectricEngine(int power, int voltage) {
        super(power);
        this.voltage = voltage;
    }
    private int voltage;

    public int getVoltage() {
        return voltage;
    }
}
