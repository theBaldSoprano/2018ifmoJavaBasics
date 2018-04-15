package com.study.itmo.gregory.lesson8;

public class Solution {

    public static void foo() throws RuntimeException{

    }
    public static void main(String[] args) {

        try {
            System.out.println(args.length);
        } catch (Exception e) {
            throw e;
        }finally {
            //todo NOTE тут можно закрыть поток
            //выполнится в ЛЮБОМ случае
            //даже если в кэчэе будет ретёрн
        }

        foo();


        try {

        }catch (NumberFormatException | NullPointerException e){
            //todo NOTE multiple exeption types
            e.getCause();
            //todo NOTE google getCause()
            new RuntimeException();
        }
    }
}

class MyExeption extends RuntimeException{
    MyExeption(String message){
        super(message);
    }

}