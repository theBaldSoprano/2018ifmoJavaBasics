package com.study.itmo.gregory.lesson11;

class Worker{

    private Object lock = new Object();

    public void doSomeWork(){
        synchronized (lock){

        }
    }
    public void doSomeOtherWork(){
        synchronized (lock){

        }
    }
    public void dooSome(){
        synchronized (this){

        }
    }
    public synchronized void doooSome(){

    }

}

public class Solution {

    public enum UserRole{
        Admin, Customer, Seller
        //опции с одним вариантом
        /**
         * уникальные опции
         * иперечисления == енамы
         * в фигурных скобках все возможные опции
         */


    }

    public UserRole getUserRole() {
        return userRole;
    }

    private UserRole userRole;

    public void foo(){
        synchronized (this){
            try {
                this.wait();
            }catch (InterruptedException e){

            }
        }
    }

    public static void main(String[] args) {

        /**
         * зачем потоки нужны?
         * 1 параллелись чтобы не ждать
         * 2 можно вместе искать по хэшмэпу
         *
         *
         */

        Solution solution = new Solution();

        solution.foo();

        System.out.println("j");


    }
}
