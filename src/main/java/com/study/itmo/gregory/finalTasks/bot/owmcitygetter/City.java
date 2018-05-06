package com.study.itmo.gregory.finalTasks.bot.owmcitygetter;

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
public class City {
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
}
