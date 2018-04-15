package com.study.itmo.gregory.lesson11;

public class Task1TenThreads extends Thread{
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            new Task1TenThreads().start();
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}
