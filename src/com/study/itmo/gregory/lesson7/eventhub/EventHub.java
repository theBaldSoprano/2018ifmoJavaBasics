package com.study.itmo.gregory.lesson7.eventhub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventHub {

    public final static EventHub EVENT_HUB = new EventHub();

    HashMap<Event, ArrayList<Subscribable>> subscribers = new HashMap<>();

    public void subscribe(Subscribable subscriber, Event event){

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

        for (Map.Entry<Event, ArrayList<Subscribable>> pair : subscribers.entrySet()){
            if(pair.getKey().getClass().equals(event.getClass())){
                for (Subscribable subscriber : pair.getValue()){
                    subscriber.onEvent(event);
                }
                return;
            }
        }
    }
}
