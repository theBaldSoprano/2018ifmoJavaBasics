package com.study.itmo.gregory.lesson7.eventhub;

public class NewFooMessage implements Event {
    @Override
    public void doSome() {
        System.out.println("Its FOO doing smth and now will be pushed to hub");
    }
}
