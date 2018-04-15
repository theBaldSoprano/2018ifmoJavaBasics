package com.study.itmo.gregory.lesson7.eventhub.events;

public class WindowClosed implements Event {
   // @Override
    public void doSome() {
        System.out.println("Window was closed");
    }

    @Override
    public String getMessage() {
        return null;
    }
}
