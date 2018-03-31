package com.study.itmo.gregory.lesson2;

public class List {

    private Node first;
    private Node last;

    private class Node {

        String line;
        public Container deed;

        Node next;
        public Node(Container deed){
            this.deed = deed;
        }
        public Node(String line) {
            this.line = line;
        }
    }

    public void add(Container deed) {
        if (first == null) {
            first = last = new Node(deed);
            return;
        }
        Node current = new Node(deed);
        last.next = current;
        last = current;
    }

    public void add(String line) {
        if (first == null) {
            first = last = new Node(line);
            return;
        }
        Node current = new Node(line);
        last.next = current;
        last = current;
    }

    public Node get(int index) {

        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public Container getLast() {
        return last.deed;
    }

    public void remove(int index) {
        if (index == 0){
            this.first = first.next;
            return;
        }
        Node left = this.get(index - 1);
        Node right = this.get(index).next;
        left.next = right;
    }

    public int size(){

        int size = 0;
        Node tmp = first;
        while (tmp != null){
            size++;
            tmp = tmp.next;
        }
        return size;
    }
}


