package com.study.itmo.gregory.lesson2;


import com.study.itmo.gregory.lesson2.list.List;

class Solution {

    public static void main(String[] args) {

        List list = new List();


        list.add("foo");
        list.add("bar");
        list.add("bar");
        list.add("bar");
        list.add("bar");
        System.out.println(list.size());
        list.remove(2);
        System.out.println(list.size());


    }

}

