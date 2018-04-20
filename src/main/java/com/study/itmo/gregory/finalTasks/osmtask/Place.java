package com.study.itmo.gregory.finalTasks.osmtask;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Place {
    @SerializedName("display_name")
    String name;

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", importance=" + importance +
                '}';
    }

    String type;
    double lat;
    double lon;
    double importance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Double.compare(place.lat, lat) == 0 &&
                Double.compare(place.lon, lon) == 0 &&
                Double.compare(place.importance, importance) == 0 &&
                Objects.equals(name, place.name) &&
                Objects.equals(type, place.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, type, lat, lon, importance);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }


}
