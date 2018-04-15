package com.study.itmo.gregory.lesson9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws ParseException {

        long time = System.currentTimeMillis(); //returns current time
        //do something

        Date date = new Date(time);

        long unixTime = date.getTime();


        long result = System.currentTimeMillis() - time;
        System.out.println(result);

        //todo NOTE календарь заменил Дэйт класс
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        int yearNow = calendar.get(Calendar.YEAR);

        System.out.println(yearNow);

        //todo NOTE simpledateformat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 =  new Date();
        System.out.println(simpleDateFormat.format(date1));


        String ds = "14.11.2018";
        Date date2 = simpleDateFormat.parse(ds);

    }



    //толстых жанна геворовна

}
