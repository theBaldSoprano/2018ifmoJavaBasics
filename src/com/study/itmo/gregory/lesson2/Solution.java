package com.study.itmo.gregory.lesson2;



class Solution {

    public static void main(String[] args) {

        Map map = new Map();

        map.put("foo", "bar");
        map.put("baz", "123");
        map.put("baeez", "1wewte23");
        map.put("btetaz", "12wetwte3");
        map.put("eteebaz", "1wet23");
        map.put("betetaz", "1wtw00000000000et23");

        Map.Node test = map.take("betetaz");

        System.out.println(test.value);



    }

}

