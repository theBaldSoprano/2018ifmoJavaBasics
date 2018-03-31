package com.study.itmo.gregory.lesson2.list;

public class StringNode extends Node {

    String line;

    @Override
    public void undo() {

    }

    public StringNode(String line) {
        this.line = line;
    }

}
