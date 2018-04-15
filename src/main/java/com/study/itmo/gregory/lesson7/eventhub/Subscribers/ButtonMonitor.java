package com.study.itmo.gregory.lesson7.eventhub.Subscribers;

import com.study.itmo.gregory.lesson7.eventhub.events.ButtonClick;
import com.study.itmo.gregory.lesson7.eventhub.events.Event;
import com.study.itmo.gregory.lesson7.eventhub.events.NewMail;
import com.study.itmo.gregory.lesson7.eventhub.events.WindowClosed;

public class ButtonMonitor implements Subscribable<NewMail> {
    //@Override
    public void onEvent(NewMail event) {

    }
}
