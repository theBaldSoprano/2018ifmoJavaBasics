package com.study.itmo.gregory.lesson9.restrictions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException, ParseException {

        HashMap<String, ArrayList<String>> data = new Informer().getDataMap("C:\\Users\\GregorySSDNB\\Documents\\data1.csv");

        for (Map.Entry<String, ArrayList<String>> pair : data.entrySet()){
            ArrayList<String> tmp = pair.getValue();
            System.out.print(pair.getKey() + "====>");
            for (String value : tmp) System.out.print(value + " : ");
            System.out.println();
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse("20170315");


        System.out.println(
                new Informer().getRestrictedRoads(
                        "C:\\Users\\GregorySSDNB\\Documents\\data.csv",
                        date
                ));



    }
}
