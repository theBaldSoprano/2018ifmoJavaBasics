package com.study.itmo.gregory.lesson5.mechanicsTask1;

public class Solution {

    public static int getTax(HasEngine machine){
        if (machine instanceof Truck){
            return machine.getEngine().getPower() * 85;
        }
        return 1;
    }

    public static void main(String[] args) {

        Automobile car = new Truck(new Engine(500));

    }
}
