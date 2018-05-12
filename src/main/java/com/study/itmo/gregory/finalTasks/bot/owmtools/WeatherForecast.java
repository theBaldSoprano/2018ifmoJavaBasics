package com.study.itmo.gregory.finalTasks.bot.owmtools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WeatherForecast {
    //todo check me
    //city name
    String name;
    //city id
    private long id;
    private Coordinates coord;
    private List<Weather> weather;
    private MainInfo main;
    private Wind wind;


    private class Weather{
        private String description;

        @Override
        public String toString() {
            return "Weather{" +
                    "description='" + description + '\'' +
                    '}';
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
    private class Wind{
        private double speed;

        @Override
        public String toString() {
            return "Wind{" +
                    "speed=" + speed +
                    '}';
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
    private class MainInfo{
        private double temp;
        private double pressure;
        private double humidity;

        @Override
        public String toString() {
            return "MainInfo{" +
                    "temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    '}';
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }
    }
    private class Coordinates {
        @Override
        public String toString() {
            return "Coordinates{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }

        private double lon;
        private double lat;

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
    }

    @Override
    public String toString() {
        return String.format("city name is %s%ncity id = %d%ncoords are %s%n" +
                "weather is %s%nmain info %s%nwind is %s", name, id, coord, weather,main, wind);
    }
}
