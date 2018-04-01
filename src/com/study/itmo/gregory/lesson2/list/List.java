package com.study.itmo.gregory.lesson2.list;

public class List <K> {

    private Node<K> first;
    private Node<K> last;

    public void add(K foo) {
        if (first == null) {
            first = last = new Node<K>(foo);
            return;
        }
        Node current = new Node<K>(foo);
        last.next = current;
        last = current;
    }


    public Node<K> get(int index) {

        Node<K> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public Node<K> getLast() {
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


