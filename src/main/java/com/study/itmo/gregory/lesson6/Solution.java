package com.study.itmo.gregory.lesson6;

import com.study.itmo.gregory.lesson6.solids.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Solution {
    //todo google Objects getclass method

    public static void main(String[] args) throws IOException {

        //Box box = new Box(10);
        //System.out.println("init box volume is: " + box.getVolume());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("input shape name - ball, pyramid or cylinder");
            String name = reader.readLine();
            Random ran = new Random();

            Shape shape = null;
            switch (name) {
                case "ball":
                    shape = new Ball(ran.nextDouble());
                    break;
                case "pyramid":
                    //shape = new Pyramid(ran.nextDouble(), ran.nextDouble());
                    break;
                case "cylinder":
                    shape = new Cylinder(ran.nextDouble(), ran.nextDouble());
                    break;
                default:
                    System.out.println("wrong name!");
            }
            if (shape != null) {
                //if (box.canAdd(shape)){
                // box.add(shape);
                // System.out.println("volume left: " + box.getVolume());
            } else System.out.println("i'm full");
        }
    }


}

