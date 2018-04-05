package com.study.itmo.gregory.lesson7.eventhub;

public class NewBazMessage implements Event {
    @Override
    public void doSome() {
        System.out.println("Its BAZ doing smth and now will be pushed to hub");
    }
}
