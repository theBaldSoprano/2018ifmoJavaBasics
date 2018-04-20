package com.study.itmo.gregory.lesson7.eventhub.Subscribers;

import com.study.itmo.gregory.lesson7.OnNewMail;


public class MailChecker{

    @OnNewMail()
    public void doSomeShit() {
        String message = "im processing the event";
        System.out.println(message);
    }
}
