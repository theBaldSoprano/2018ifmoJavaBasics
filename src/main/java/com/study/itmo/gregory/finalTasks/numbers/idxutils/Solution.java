package com.study.itmo.gregory.finalTasks.numbers.idxutils;

import com.google.common.collect.TreeMultiset;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        LinkedList<Neighbor> neighbors = new LinkedList<>();

        neighbors.add(new Neighbor(4, 0.66));
        neighbors.add(new Neighbor(4, 5));
        neighbors.add(new Neighbor(4, 0.1));
        neighbors.add(new Neighbor(4, 0.1));

        Collections.sort(neighbors);
        neighbors.getLast().getLengthBetween();

        for (Neighbor n : neighbors) System.out.println(n.toString());



    }
}
