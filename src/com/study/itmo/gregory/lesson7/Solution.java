package com.study.itmo.gregory.lesson7;

import java.lang.*;
import java.util.Arrays;
import java.util.HashMap;

import static java.lang.System.out;
//todo read about imports and static imports


class A implements Runnable{
    @Override
    public void run() {

    }
}
interface Closable extends Runnable{
    //todo NOTE interface extends interface

    public static final int FOO = 999;
    //todo NOTE в интерфейсе может быть константа

}

class B extends A {
}

class C extends B {
}

interface Runnable {
    //todo NOTE интерфейс описывает поведение!!
    void run();
}

public class Solution {

    public void foo(A a) {
    }

    public void foo(B a) {
    }

    public void foo(Object a) {
    }

    //todo check!!!!!!
    public static void main(String[] args) {

        //out.print(22);

        String [] strings = {"foo", "barrrr", "za"};

        ComparatorByLength cbl = new ComparatorByLength();

        Arrays.sort(strings, cbl);

        System.out.println(Arrays.toString(strings));

        Runnable runnable = new A();
        //todo NOTE interface can be type

        A a = new A();


    }


}

interface List{
    //todo NOTE в интерфейсе нет состояний

    int size();

    default boolean isEmpty(){

        new A();
        return size() == 0;
    }
    //todo NOTE можно реализовать по дефолту
    //но только


}
