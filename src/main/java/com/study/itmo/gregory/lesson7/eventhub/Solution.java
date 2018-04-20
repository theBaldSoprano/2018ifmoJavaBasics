package com.study.itmo.gregory.lesson7.eventhub;


import com.study.itmo.gregory.lesson7.eventhub.Subscribers.ButtonMonitor;
import com.study.itmo.gregory.lesson7.eventhub.Subscribers.GUIController;
import com.study.itmo.gregory.lesson7.eventhub.Subscribers.MailChecker;
import com.study.itmo.gregory.lesson7.eventhub.events.ButtonClick;
import com.study.itmo.gregory.lesson7.eventhub.events.NewMail;
import com.study.itmo.gregory.lesson7.eventhub.events.WindowClosed;

import java.lang.reflect.InvocationTargetException;

public class Solution {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        NewMail bar = new NewMail(4);


        MailChecker mailChecker = new MailChecker();


        EventHub.getInstance().subscribe(mailChecker);


        EventHub.getInstance().push(bar);


    }
}
