package com.study.itmo.gregory.lesson7.eventhub;

public class SubscriberInky implements Subscribable {

    @Override
    public void onEvent(Event event) {
        if (event instanceof NewBarMessage) {
            System.out.println("I'm subscriber INKY and i am suscribed on BAR");
        }
    }
}
