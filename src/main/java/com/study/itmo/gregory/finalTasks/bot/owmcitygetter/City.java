package com.study.itmo.gregory.finalTasks.bot.owmcitygetter;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * class placeholder for
 * city Entity from Open Weather Map db
 * info is provided by json file
 */
/*{      EXAMPLE
        "id": 6460975,
        "name": "Rastnik",
        "country": "BG",
        "coord": {
        "lon": 25.283331,
        "lat": 41.400002
        }
}*/
public class City implements Comparable<City>{

    public City(City city, Double diff){
        this.country = city.getCountry();
        this.id = city.getId();
        this.name = city.getName();
        this.lat = city.getLat();
        this.lon = city.getLon();
        this.diff = diff;
    }

    public City(String name) {
        this.name = name;
    }

    private int id;
    private String name;
    private String country;
    private double lon;
    private double lat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private Double diff;

    public Double getDiff() {
        return diff;
    }
    public void setDiff(Double diff) {
        this.diff = diff;
    }

    @Override
    public int compareTo(@NotNull City o2) {
        return this.getDiff().compareTo(o2.getDiff());
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", diff=" + diff +
                '}';
    }
}
