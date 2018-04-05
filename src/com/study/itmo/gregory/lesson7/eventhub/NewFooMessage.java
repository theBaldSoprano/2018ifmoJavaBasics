package com.study.itmo.gregory.lesson7.eventhub;

import static com.study.itmo.gregory.lesson7.eventhub.EventHub.EVENT_HUB;

public class NewFooMessage implements Event {
    @Override
    public void doSome() {
        System.out.println("Its FOO doing smth and now will be pushed to hub");
        EVENT_HUB.push(this);
    }
}
