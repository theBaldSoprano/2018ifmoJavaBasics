package com.study.itmo.gregory.lesson11.threadtasks.task3;

import com.study.itmo.gregory.lesson11.threadtasks.MyThread;

import java.util.ArrayList;

public class Data {
    private ArrayList<Double> doubles = new ArrayList<>();

    public int size(){
        return doubles.size();
    }

    private Object monitor = new Object();
    //это должен быть один мишка на всех потоков


    public void add(Double element){
        synchronized (monitor) {
            doubles.add(element);
        }
    }
}

class Solution{
    public static void main(String[] args) throws InterruptedException {

        Data data = new Data();

        for (int i = 0; i < 100; i++) {
            new MyThread(data).start();
        }
        Thread.sleep(5000);
        System.out.println(data.size());

    }
}
