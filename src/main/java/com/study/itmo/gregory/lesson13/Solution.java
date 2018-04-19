package com.study.itmo.gregory.lesson13;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Collection collection;

        /**
         * 1
         * 2
         * 3
         * 4
         * 5
         * 6
         */

        List list = new ArrayList();
        List list2 = new LinkedList();

        Set<String> set = new HashSet<>();
        set = new TreeSet<>();

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for (String s : set){
            System.out.println(s);
        }

        //todo google guava for collections
        //togo google Queue Deque Kist Set Map

        Queue<String> stringQueue;


        Map<String, String> map = new HashMap<>();

        //как обходить мапы
        //1
        for (String key : map.keySet()){
            //get set of keyes from map
        }
        //2
        for (String value : map.values()){
            //we get all values
        }
        //3
        for (Map.Entry<String, String> entry : map.entrySet()){
            //we get a Set with entries from map
        }

        //todo google binary search

        Collections.emptyList();

        //можно сравнивать создавая на хчу компаратор нужный
        //а так же можно имплементить у своего класа интерфейс Компарабл
        //и задавать там логику сравнения
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

    }

}
