package com.study.itmo.gregory.lesson2.list;

public class Node<T> {

    private T data;

    Node<T> next;

    public Node(T data) {

        this.data = data;
    }

    public T getData() {
        return data;
    }
}
