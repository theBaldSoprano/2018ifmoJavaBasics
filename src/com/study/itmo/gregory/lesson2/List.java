package com.study.itmo.gregory.lesson2;

public class List {

    private class Node {

        String line;
        Node next;

        public Node(String line) {
            this.line = line;
        }

    }

    Node first;

    Node last;

    void add(String line) {
        if (first == null) {
            first = last =  new Node(line);
            return;
        }
        Node current = new Node(line);
        last.next = current;
        last = current;
    }

    public String get(int index){
        Node current  = first;
        for (int i = 0; i < index; i++) {
            current = first.next;
        }
        return current.line;
    }

    public void delete(int index){

        //todo

    }
}


