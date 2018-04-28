package com.study.itmo.gregory.lesson16;

@MyAnno(trueIdTableName = "ArtistId", trueTableName = "Artist")
public class ArtistEntity {
    @IsField
    @IsId
    int artistId;
    @IsField
    String name;

    public ArtistEntity(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }
    public ArtistEntity(){}

    @Override
    public String toString() {
        return String.format("[%d] %s", artistId, name);
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
