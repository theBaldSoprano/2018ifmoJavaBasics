package com.study.itmo.gregory.lesson11.threadtasks;

import com.study.itmo.gregory.lesson11.threadtasks.task3.Data;

import java.util.Date;
import java.util.Random;

class Task2ThreadState {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread(new Data());//создали объект потока
        thread.start();//и запустили поток
        thread.join();//и  ПРИОСТАНОВИМ МЭЙН ТРЭД ДО ТОГО МОМЕНТА ПОКА
        //thread НЕ ЗАКОНЧИТСЯ
        System.out.println(thread.getState());//и пишем и заканчиваемся
    }
}

public class MyThread extends Thread{

    public MyThread(Data data){
        this.data = data;
        myR = new Random();
        System.out.println(this.getState());//выведем когда создадим объект
    }
    Data data;
    Random myR;

    @Override
    public void run() {
        System.out.println(this.getState());//только что вызвали ран и сообщили об этом
        for (int i = 0; i < 100; i++) {
            data.add(myR.nextDouble());
        }
    }
}
