package com.study.itmo.gregory.lesson5.mystringbuilderTask3;

public class Solution {
    public static void main(String[] args) {

        MyStringBuilder bld = new MyStringBuilder("foo");
        System.out.println(bld.toString());

        bld.append("bar");
        System.out.println(bld.toString());

        bld.append("baz");
        System.out.println(bld.toString());

        bld.undo();
        System.out.println(bld.toString());

        bld.undo();
        System.out.println(bld.toString());


    }
}
