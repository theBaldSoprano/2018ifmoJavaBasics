package com.study.itmo.gregory.lesson7.eventhub.Subscribers;

import com.study.itmo.gregory.lesson7.eventhub.events.ButtonClick;
import com.study.itmo.gregory.lesson7.eventhub.events.WindowClosed;

public class GUIController{
    //@Override
    public void onEvent(WindowClosed event) {

            System.out.println("I'm subscriber PINKY and i am suscribed on BAZ");

    }
}
