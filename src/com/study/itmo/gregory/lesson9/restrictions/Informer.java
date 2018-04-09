package com.study.itmo.gregory.lesson9.restrictions;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Informer implements RoadChecker {


    @Override
    public int getRestrictedRoads(String fileName, Date date) throws IOException, ParseException {
        int amount = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        HashMap<String, ArrayList<String>> data = getDataMap(fileName);
        //get amount of lines
        int size = data.get("kod").size();
        //for each line
        for (int i = 0; i < size; i++) {
            String beginDate = data.get("d.n.ogr").get(i);
            String endDate = data.get("data_snjatija_ogr").get(i);
            Date begin = simpleDateFormat.parse(beginDate);

            if (endDate.equals("-")) {
                if (date.after(begin)) amount++;
            } else {
                Date end = simpleDateFormat.parse(endDate);
                if (date.after(begin) && date.before(end)) amount++;
            }
        }
        return amount;
    }

    public HashMap<String, ArrayList<String>> getDataMap(String filename) throws IOException {
        //prepare readers
        FileInputStream fis = new FileInputStream(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        //map to hold data
        HashMap<String, ArrayList<String>> data = new HashMap<>();
        //read first line with column names
        String line = reader.readLine();
        //parse it
        String[] tmp = line.split(",");
        //add only ID, District, Address, beginDate, endDate
        ArrayList<String> columns = new ArrayList<>();
        columns.add(tmp[0]);
        columns.add(tmp[6]);
        columns.add(tmp[7]);
        columns.add(tmp[10]);
        columns.add(tmp[12]);
        //init keys in data map with needed columns
        for (String column : columns) data.put(column, new ArrayList<>());

        while ((line = reader.readLine()) != null) {
            tmp = line.split(",");
            data.get(columns.get(0)).add(tmp[0]);
            data.get(columns.get(1)).add(tmp[6]);
            data.get(columns.get(2)).add(tmp[7]);
            data.get(columns.get(3)).add(tmp[10]);
            data.get(columns.get(4)).add(tmp[12]);
        }
        return data;
    }
}
