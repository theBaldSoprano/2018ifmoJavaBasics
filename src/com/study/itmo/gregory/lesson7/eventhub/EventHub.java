package com.study.itmo.gregory.lesson7.eventhub;

import java.util.ArrayList;
import java.util.HashMap;

public class EventHub {

    private static EventHub instance;

    public static synchronized EventHub getInstance(){
        if (instance == null)
            instance = new EventHub();
        return instance;
    }

    HashMap<Class, ArrayList<Subscribable>> subscribers = new HashMap<>();

    public void subscribe(Subscribable subscriber, Class event){

        if (!subscribers.containsKey(event)){
            subscribers.put(event, new ArrayList<Subscribable>());
            subscribers.get(event).add(subscriber);
            return;
        }
        if (!subscribers.get(event).contains(subscriber)){
            subscribers.get(event).add(subscriber);
        }
    }
    public void push(Event event){
        for(Subscribable subscribable : subscribers.get(event.getClass())){
            subscribable.onEvent(event);
        }
    }
}
