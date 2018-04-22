package com.study.itmo.gregory.lesson11.threadtasks.task4;

import java.util.concurrent.ThreadPoolExecutor;

public class Swap {
    public static void main(String[] args) {

        MyThread t1 = new MyThread("foo");
        MyThread t2 = new MyThread("bar");
        t1.start();
        t2.start();

    }


}

class MyThread extends Thread {
  //  public static Thread last;

    private static final Object monitor = new Object();

    private String message;

    public MyThread(String m) {
        message = m;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            while (true) {

               // if (last == this) {
                    try {

                        System.out.println(message);
                        monitor.notify();
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //} else {

                //    last = this;
               // }

            }
        }
    }
}