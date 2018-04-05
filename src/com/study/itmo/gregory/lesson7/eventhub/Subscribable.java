package com.study.itmo.gregory.lesson7.eventhub;

interface Subscribable {
    void onEvent(Event event);
}
