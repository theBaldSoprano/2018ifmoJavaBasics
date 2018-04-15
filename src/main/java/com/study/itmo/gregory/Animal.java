package com.study.itmo.gregory;

public class Animal {

    public void eat(String h){ }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.eat(999);
        cat.eat("");

    }


}

class Cat extends Animal{

    public void eat(double n){

    }


}
