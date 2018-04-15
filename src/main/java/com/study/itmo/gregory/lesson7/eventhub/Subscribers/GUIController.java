package com.study.itmo.gregory.lesson7.eventhub.Subscribers;

import com.study.itmo.gregory.lesson7.eventhub.events.ButtonClick;
import com.study.itmo.gregory.lesson7.eventhub.events.Event;

public class GUIController implements Subscribable{
    //@Override
    public void onEvent(Event event) {
        if (event instanceof ButtonClick) {
            System.out.println("I'm subscriber PINKY and i am suscribed on BAZ");
        }
    }
}
