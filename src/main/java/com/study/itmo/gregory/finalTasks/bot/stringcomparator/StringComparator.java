package com.study.itmo.gregory.finalTasks.bot.stringcomparator;

import com.study.itmo.gregory.finalTasks.bot.owmcitygetter.City;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import static com.study.itmo.gregory.finalTasks.bot.owmcitygetter.OWMTools.getCities;
import static com.study.itmo.gregory.finalTasks.bot.owmcitygetter.OWMTools.getJsonCitiesFile;
import static com.study.itmo.gregory.finalTasks.bot.owmcitygetter.OWMTools.pullCitiesFile;

public class StringComparator {
    public static void main(String[] args) throws IOException {

        pullCitiesFile();
        List<City> cities = getCities(getJsonCitiesFile());
        TreeSet<City> sugg = getSuggestionMap(cities, new City("Saint-petersburg"));

        for (City c : sugg) System.out.println(c.getName());

        /*System.out.println(getDiff("Moscow", "Moscow"));
        System.out.println(getDiff("Moscow", "Mosvow"));
        System.out.println(getDiff("Geeral", "Mosvow"));
        System.out.println(getDiff("Saint-Petersburg", "Mosvow"));*/


    }
    public static Double getDiff(String s1, String s2) {
        s1 = s1.replaceAll("[ -.,]", "");
        s2 = s2.replaceAll("[ -.,]", "");
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int count = 0;
        int minL = 0;

        if (s1.length() < s2.length()) minL = s1.length();
        else minL = s2.length();

        for (int i = 0; i < minL; i++) {
            if (s1.charAt(i) == s2.charAt(i)) count++;
        }
        return (double)count / ((double) s1.length() + (double)s2.length() - (double)count);
    }

    public static TreeSet<City> getSuggestionMap(List<City> cities, City input){
        TreeSet<City> suggestions = new TreeSet<>();
        for (City city: cities){
            Double diff = getDiff(city.getName(), input.getName());
            if (suggestions.size() > 2){
                if (diff > suggestions.first().getDiff()){
                    suggestions.remove(suggestions.first());
                    suggestions.add(new City(city, diff));
                }
            }else {
                suggestions.add(new City(city, diff));
            }
        }
        return suggestions;
    }
}
