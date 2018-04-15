package com.study.itmo.gregory.lesson7.eventhub.Subscribers;

import com.study.itmo.gregory.lesson7.eventhub.events.NewMail;

public class MailChecker implements Subscribable <NewMail> {
    //@Override
    public void onEvent(NewMail event) {
        System.out.println(event.getMessage());
    }
}
