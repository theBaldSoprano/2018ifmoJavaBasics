package com.study.itmo.gregory.lesson2.list;
import com.study.itmo.gregory.lesson2.Container;

public class ContainerNode extends Node{

    public Container deed;

    @Override
    public void undo() {
        deed.undo();
    }

    public ContainerNode(Container deed){
        this.deed = deed;
    }

}
