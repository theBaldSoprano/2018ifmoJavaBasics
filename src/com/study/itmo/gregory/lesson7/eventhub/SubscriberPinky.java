package com.study.itmo.gregory.lesson7.eventhub;

public class SubscriberPinky implements Subscribable{
    @Override
    public void onEvent(Event event) {
        if (event instanceof NewBazMessage) {
            System.out.println("I'm subscriber PINKY and i am suscribed on BAZ");
        }
    }
}
