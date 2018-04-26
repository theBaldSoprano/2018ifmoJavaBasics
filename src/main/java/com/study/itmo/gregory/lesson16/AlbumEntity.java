package com.study.itmo.gregory.lesson16;

public class AlbumEntity {
    int id;
    String title;

    public AlbumEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }
    @Override
    public String toString() {
        return String.format("[%d] %s", id, title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
