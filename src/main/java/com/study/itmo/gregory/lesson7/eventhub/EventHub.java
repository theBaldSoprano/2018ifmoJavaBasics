package com.study.itmo.gregory.lesson7.eventhub;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

public class EventHub {

    private static EventHub instance;

    public static synchronized EventHub getInstance(){
        if (instance == null)
            instance = new EventHub();
        return instance;
    }

    HashSet subscribers = new HashSet();

    //HashSet<Subscribable<? extends Event>> subscribers = new HashSet<>();

    //ArrayList<Subscribable<? extends Event>> s = new ArrayList<>();


    public void subscribe(Object subscriber){
        if (!subscribers.contains(subscriber))subscribers.add(subscriber);
    }

    public void push(Object event) throws InvocationTargetException, IllegalAccessException {
        for (Object sub : subscribers){
            for(Method method : sub.getClass().getMethods()){
                for (Annotation annotation : event.getClass().getDeclaredAnnotations()){
                    if (method.isAnnotationPresent(annotation.annotationType())){
                        method.invoke(sub);
                    }
                }
            }
        }
    }
}
