package com.study.itmo.gregory.finalTasks.osmtask;

import com.study.itmo.gregory.lesson2.list.List;

import java.io.IOException;

import static com.study.itmo.gregory.finalTasks.osmtask.OpenStreetMapProcessor.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        java.util.List<Place> places;
        places = processUrl(getUrl(getSearchRequest()));
        for (Place place : places){
            System.out.println(place.toString());
        }

    }
}
