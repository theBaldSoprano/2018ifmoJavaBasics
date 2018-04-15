package com.study.itmo.gregory.lesson9.restrictions;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by rpetrov on 12/9/16.
 */
public interface RoadChecker {

    /* you can use

    Files.readAllLines(Paths.get(fileName)).toArray(new String[]{});

    to read files to string array
     */

    int getRestrictedRoads(String fileName, Date date) throws IOException, ParseException;
}

