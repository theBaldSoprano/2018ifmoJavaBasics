package com.study.itmo.gregory.lesson7.eventhub;


public class NewBarMessage implements Event {
    @Override
    public void doSome() {
        System.out.println("Its BAR doing smth and now will be pushed to hub");
    }
}
