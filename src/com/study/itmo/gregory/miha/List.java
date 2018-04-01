package com.study.itmo.gregory.miha;

public class List {

    String data;
    List next;

    public List(String s) {
        this.data = s;
    }


    public void add(String s) {
        if (this.next == null) {
            this.next = new List(s);
        } else next.add(s);
    }

    public List get(int n) {

        return n == 0 ? this : this.next.get(n - 1);

    }


}
