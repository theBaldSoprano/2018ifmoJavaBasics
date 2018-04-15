package com.study.itmo.gregory.lesson7.eventhub;


import com.study.itmo.gregory.lesson7.eventhub.Subscribers.ButtonMonitor;
import com.study.itmo.gregory.lesson7.eventhub.Subscribers.GUIController;
import com.study.itmo.gregory.lesson7.eventhub.Subscribers.MailChecker;
import com.study.itmo.gregory.lesson7.eventhub.events.ButtonClick;
import com.study.itmo.gregory.lesson7.eventhub.events.NewMail;
import com.study.itmo.gregory.lesson7.eventhub.events.WindowClosed;

public class Solution {
    public static void main(String[] args) {

        WindowClosed foo = new WindowClosed();
        //NewMail bar = new NewMail();
        ButtonClick baz = new ButtonClick();

        ButtonMonitor zaz = new ButtonMonitor();
        GUIController pinky = new GUIController();
        MailChecker inky = new MailChecker();

        EventHub.getInstance().subscribe(zaz);
       // EventHub.getInstance().subscribe(zaz, NewMail.class);
       // EventHub.getInstance().subscribe(zaz, ButtonClick.class);
       // EventHub.getInstance().subscribe(pinky, ButtonClick.class);
       // EventHub.getInstance().subscribe(inky, NewMail.class);

        foo.doSome();
        EventHub.getInstance().push(foo);
        System.out.println("*********************");
        //bar.doSome();
        //EventHub.getInstance().push(bar);
        System.out.println("*********************");
        baz.doSome();
        EventHub.getInstance().push(baz);


    }
}
