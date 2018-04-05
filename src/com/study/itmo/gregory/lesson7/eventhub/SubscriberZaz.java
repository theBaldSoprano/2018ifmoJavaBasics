package com.study.itmo.gregory.lesson7.eventhub;

public class SubscriberZaz implements Subscribable {
    @Override
    public void onEvent(Event event) {
        if (event instanceof NewFooMessage) {
            System.out.println("I'm subscriber ZAZ and i am suscribed on FOO");
        } else if (event instanceof NewBarMessage) {
            System.out.println("I'm subscriber ZAZ and i am suscribed on BAR");
        } else if (event instanceof NewBazMessage) {
            System.out.println("I'm subscriber ZAZ and i am suscribed on BAZ");
        }
    }
}
