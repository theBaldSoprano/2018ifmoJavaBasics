package com.study.itmo.gregory.lesson2.list;

import com.study.itmo.gregory.lesson2.Container;

public class List {

    private Node first;
    private Node last;

    public void add(Container deed) {
        if (first == null) {
            first = last = new ContainerNode(deed);
            return;
        }
        Node current = new ContainerNode(deed);
        last.next = current;
        last = current;
    }

    public void add(String line) {
        if (first == null) {
            first = last = new StringNode(line);
            return;
        }
        Node current = new StringNode(line);
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

    public Node getLast() {
        return last;
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


