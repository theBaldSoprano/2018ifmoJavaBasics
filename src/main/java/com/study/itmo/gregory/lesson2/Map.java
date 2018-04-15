package com.study.itmo.gregory.lesson2;


public class Map {

    class Node {

        String key;
        String value;

        Node left;
        Node right;

        public Node(String key, String value) {

            this.key = key;
            this.value = value;

        }

    }

    Node first = null;


    void add(Node node, String key, String value) {

        if (node.key.hashCode() == key.hashCode()) {
            node.value = value;

        } else if (node.key.hashCode() < key.hashCode()) {
            if (node.right != null) {
                add(node.right, key, value);
            } else node.right = new Node(key, value);

        } else if (node.key.hashCode() > key.hashCode()) {
            if (node.left != null) {
                add(node.left, key, value);
            } else node.left = new Node(key, value);

        }

    }

    void put(String key, String value) {

        if (first == null) first = new Node(key, value);
        else add(first, key, value);

    }

    Node get(Node current, String key) {

        if (current.key.hashCode() == key.hashCode()) {
            return current;
        } else if (current.key.hashCode() < key.hashCode()) {
            return get(current.right, key);
        } else {
            return get(current.left, key);
        }
    }


    Node take(String key) {

        if (first.key.hashCode() == key.hashCode()) {
            return first;
        } else {
            return get(first, key);
        }


    }


}
