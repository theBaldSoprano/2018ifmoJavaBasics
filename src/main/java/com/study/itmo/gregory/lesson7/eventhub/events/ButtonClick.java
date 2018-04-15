package com.study.itmo.gregory.lesson7.eventhub.events;

public class ButtonClick implements Event {
    //@Override
    public void doSome() {
        System.out.println("Button was clicked");
    }

    @Override
    public String getMessage() {
        return null;
    }
}
