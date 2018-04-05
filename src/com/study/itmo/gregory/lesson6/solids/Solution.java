package com.study.itmo.gregory.lesson6.solids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {

        ArrayList<IsShape> shapes = new ArrayList<>();


        shapes.add(new Ball(1345345345));
        shapes.add(new Ball(1));
        shapes.add(new Ball(14444));


        System.out.println(shapes.toString());

        Collections.sort(shapes);

        System.out.println(shapes.toString());



    }
}
