package com.study.itmo.gregory.lesson7.eventhub.Subscribers;

import com.study.itmo.gregory.lesson7.eventhub.events.Event;

import java.lang.reflect.ParameterizedType;

public interface Subscribable <T extends Event> {

    /**
     * этот метод нужен
     * чтобы любой входящий ивент был приведен к конкретному
     * типу из области <T extends Event>
     *     то есть Ивент или его наследник
     * в этом месте у нас не может быть приведения типа к типу которые
     * находятся ппараллельно
     * зато это было возможно в EventHub
     * но теперь подписчик параметризированный
     * некоторым типом (например NewMail)
     * там
     * вызовет onInternalEvent
     * здесь Т станет NewMail
     * но в if в ивенотхабе мы уже точно проверили -
     * является ли event экземпляром
     * NewMail
     * так что все приведется точно
     * @param event
     */
    default void onInternalEvent(Event event){
        onEvent((T)event);
    };

    /**
     * onEvent() это место для кастомной логики
     * для определенного объекта ивента
     * в определенном подписчике
     * @param t
     */
    void onEvent(T t);

}


/**
 * todo чтобы подписчики - то есть интерфейс
 * будет параметризован  чтобы не отправлять там тип
 * гугл как получить параметр объекта и вытащить из сабскрипшна
 * а в onEvent чтобы приходил именно параметризованный
 */
