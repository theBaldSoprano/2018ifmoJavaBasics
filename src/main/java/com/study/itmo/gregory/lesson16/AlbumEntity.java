package com.study.itmo.gregory.lesson16;

@MyAnno(trueIdTableName = "AlbumId", trueTableName = "Album")
public class AlbumEntity {
    @IsField
    @IsId
    int albumId;
    @IsField
    String title;

    public AlbumEntity(int albumId, String title) {
        this.albumId = albumId;
        this.title = title;
    }
    public AlbumEntity(){}
    @Override
    public String toString() {
        return String.format("[%d] %s", albumId, title);
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
