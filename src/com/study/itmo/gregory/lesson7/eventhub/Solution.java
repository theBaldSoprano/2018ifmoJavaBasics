package com.study.itmo.gregory.lesson7.eventhub;


public class Solution {
    public static void main(String[] args) {

        NewFooMessage foo = new NewFooMessage();
        NewBarMessage bar = new NewBarMessage();
        NewBazMessage baz = new NewBazMessage();

        SubscriberZaz zaz = new SubscriberZaz();
        SubscriberPinky pinky = new SubscriberPinky();
        SubscriberInky inky = new SubscriberInky();

        EventHub.getInstance().subscribe(zaz, NewFooMessage.class);
        EventHub.getInstance().subscribe(zaz, NewBarMessage.class);
        EventHub.getInstance().subscribe(zaz, NewBazMessage.class);
        EventHub.getInstance().subscribe(pinky, NewBazMessage.class);
        EventHub.getInstance().subscribe(inky, NewBarMessage.class);

        foo.doSome();
        EventHub.getInstance().push(foo);
        System.out.println("*********************");
        bar.doSome();
        EventHub.getInstance().push(bar);
        System.out.println("*********************");
        baz.doSome();
        EventHub.getInstance().push(baz);


    }
}
