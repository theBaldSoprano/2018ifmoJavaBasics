package com.study.itmo.gregory.lesson16;

@MyAnno(trueIdTableName = "TrackId", trueTableName = "Track")
public class TrackEntity {
    @IsField
    @IsId
    int trackId;
    @IsField
    String name;
    @IsField
    String composer;

    public TrackEntity(int trackId, String name, String composer) {
        this.trackId = trackId;
        this.name = name;
        this.composer = composer;
    }

    public TrackEntity() {
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    @Override
    public String toString() {
        return "TrackEntity{" +
                "trackId=" + trackId +
                ", name='" + name + '\'' +
                ", composer='" + composer + '\'' +
                '}';
    }
}
