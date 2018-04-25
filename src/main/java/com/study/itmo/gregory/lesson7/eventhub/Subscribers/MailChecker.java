package com.study.itmo.gregory.lesson7.eventhub.Subscribers;

import com.study.itmo.gregory.lesson7.OnNewMail;
import com.study.itmo.gregory.lesson7.eventhub.Subscribe;
import com.study.itmo.gregory.lesson7.eventhub.events.NewMail;


public class MailChecker{

    @Subscribe()
    public void doSomeShit(NewMail newMail) {
        String message = "im processing the event";
        System.out.println(message);
    }
}
