package com.study.itmo.gregory.lesson7.eventhub;

import static com.study.itmo.gregory.lesson7.eventhub.EventHub.EVENT_HUB;

public class Solution {
    public static void main(String[] args) {

        NewFooMessage foo = new NewFooMessage();
        NewBarMessage bar = new NewBarMessage();
        NewBazMessage baz = new NewBazMessage();

        SubscriberZaz zaz = new SubscriberZaz();
        SubscriberPinky pinky = new SubscriberPinky();
        SubscriberInky inky = new SubscriberInky();

        //EVENT_HUB.subscribe(zaz, new NewFooMessage());
        //EVENT_HUB.subscribe(zaz, new NewBarMessage());
        //EVENT_HUB.subscribe(zaz, new NewBazMessage());
        EVENT_HUB.subscribe(pinky, new NewBazMessage());
        EVENT_HUB.subscribe(inky, new NewBarMessage());

        foo.doSome();
        System.out.println("*********************");
        bar.doSome();
        System.out.println("*********************");
        baz.doSome();


    }
}
