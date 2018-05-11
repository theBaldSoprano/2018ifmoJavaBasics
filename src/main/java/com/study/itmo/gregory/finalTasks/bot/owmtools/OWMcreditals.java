package com.study.itmo.gregory.finalTasks.bot.owmtools;

/**
 * here comes creditals you will need while work with open weather map
 * apikey - your own key
 * cities json adress - returns a json with all cities and their id and other stuff
 */
public class OWMcreditals {
    public static final String API_KEY = "lol";
    public static final String CITIES_JSON_GZ_ADDRESS = "http://bulk.openweathermap.org/sample/city.list.json.gz";
    public static final String HOME = "C:\\weatherbot\\";
    public static final String GZ_CITIES_FILE = String.format("%scity.list.json.gz", HOME);
    public static final String JSON_CITIES_FILE = String.format("%scity.list.json", HOME);


}
