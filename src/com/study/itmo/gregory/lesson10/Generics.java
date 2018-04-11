package com.study.itmo.gregory.lesson10;

class FOO<T, V>{

    private T t;
    private V v;



    public void set(V v) {
        this.v = v;
    }
}

class Goo<T>{

    private T t;
    public Goo() {
        //t.getClass().getConstructors()[0].newInstance();
        //todo !!!!! google REFLECTIONS
        //this.t = new T();
    }
}

class A<T>{
    T t;

    public A(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void print(){
        System.out.println(t);
    }
}

class B extends A<Double>{


    public B(Double o) {
        super(o);
    }
}
class C<G> extends A<G>{



    public C(G aDouble) {
        super(aDouble);
    }
}

public class Generics {
    public static void main(String[] args) {

        SuperBox box1 = new SuperBox();
        SuperBox<Object> box2 = new SuperBox<Object>();

        Box box3 = new Box();
        box3.setObject("gggg");


    }

}
class Box{
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

class ExtBox<T extends Number>{


    public void doSome(ExtBox<? extends Double>box){
        //входящий аргумент(?) является Double или его наследником
        //todo NOTE GENERICS
    }


}

class SuperExtBox<T extends Number & Cloneable>{


    public void doSome(ExtBox<? super Double>box){
        //входящий аргумент(?) является супер по отношению к Double
    }

}

class SuperBox <T>{
    private T object;

    public T getObject() {
        return object;
    }

    public <T> T doSome(T foo){
        return foo;
        //todo NOTE example of parameterized method
    }


    public void setObject(T object) {
        this.object = object;
    }
}

class GiperBox<T, G>{

    private T obj1;
    private G obj2;

    public T getObj1() {
        return obj1;
    }

    Number foo;

    public void setObj1(T obj1) {
        this.obj1 = obj1;
    }

    public G getObj2() {
        return obj2;
    }

    public void setObj2(G obj2) {
        this.obj2 = obj2;
    }
}