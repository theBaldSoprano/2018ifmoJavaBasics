package com.study.itmo.gregory.lesson7.eventhub;

import com.study.itmo.gregory.lesson7.eventhub.Subscribers.Subscribable;
import com.study.itmo.gregory.lesson7.eventhub.events.Event;
import com.study.itmo.gregory.lesson7.eventhub.events.NewMail;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EventHub {

    private static EventHub instance;

    public static synchronized EventHub getInstance(){
        if (instance == null)
            instance = new EventHub();
        return instance;
    }

    HashSet<Subscribable<? extends Event>> subscribers = new HashSet<>();

    //ArrayList<Subscribable<? extends Event>> s = new ArrayList<>();


    public void subscribe(Subscribable<? extends Event> subscriber){
        if (!subscribers.contains(subscriber))subscribers.add(subscriber);
    }

    public <T extends Event> void push(T event){

        for (Subscribable<? extends Event> sub : subscribers){

            /**
             * ПОТОМУ ЧТО проверку на равность объектов я проверяю кастомно
             * ифом
             * а компилятор видит, что если проверки нет
             * то sub и event могут оказаться например ButtonClick а подписчик на NewMail
             */
            if ( ((ParameterizedType) event.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getClass().equals(event.getClass())){
                sub.onInternalEvent(event);
            }

        }
    }
}
