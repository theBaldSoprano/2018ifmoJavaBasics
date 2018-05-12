package com.study.itmo.gregory.finalTasks.bot.owmtools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.telegram.telegrambots.api.objects.Location;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.study.itmo.gregory.finalTasks.bot.Creditals.OWMTOKEN;
import static com.study.itmo.gregory.finalTasks.bot.owmtools.OWMcreditals.*;

public class OWMTools {
    public static void main(String[] args) throws IOException {
        /*pullCitiesFile();
        int lol = 0;
        List<City> cities = getCities(getUnzippedJsonCitiesFile());
        City city = cities.get(0);
        System.out.println(city.toString());*/
        Float lat = -16.92F;
        Float lon = 145.77F;

    }

    //GETTING CITIES LIST
    public static void pullCitiesFile() throws IOException {
        //prepare URL and establish http connection
        URL url = new URL(CITIES_JSON_GZ_ADDRESS);
        URLConnection urlConnection = url.openConnection();
        if (!(urlConnection instanceof HttpURLConnection)) throw new IllegalArgumentException();
        HttpURLConnection httpConn = (HttpURLConnection) urlConnection;

        //get response as InputStream and write it to array while possible
        int length = httpConn.getContentLength();
        InputStream is = new BufferedInputStream(httpConn.getInputStream());
        byte[] data;
        try {
            data = new byte[length];
            int offset = 0;
            while (offset < length) {
                int read = is.read(data, offset, data.length - offset);
                if (read < 0) {
                    break;
                }
                offset += read;
            }
            if (offset < length) {
                throw new IOException(String.format("Read %d bytes; expected %d", offset, length));
            }
        } finally {
            is.close();
        }
        //write bytes to local file specified in creds
        FileUtils.writeByteArrayToFile(new File(GZ_CITIES_FILE), data, false);
        //return path to this created file
        //return Paths.get(GZ_CITIES_FILE);
    }

    public static Path getUnzippedJsonCitiesFile() throws IOException {
        GzipCompressorInputStream in = new GzipCompressorInputStream(new FileInputStream(GZ_CITIES_FILE));
        IOUtils.copy(in, new FileOutputStream(JSON_CITIES_FILE));
        return Paths.get(JSON_CITIES_FILE);
    }

    public static List<City> getCities(Path path) throws IOException {
        InputStream is = Files.newInputStream(path);
        Gson json = new Gson();
        Type type = new TypeToken<List<City>>() {
        }.getType();

        return json.fromJson(new InputStreamReader(is), type);
    }

    //GETTING WEATHER BY CITY ID
    public static WeatherForecast getWeather(City city) throws IOException {
        String tmp = String.format(WEATHER_ADDRESS_BYID, city.getId(), OWMTOKEN);
        System.out.println("mark");
        System.out.println(tmp);
        URL url = new URL(tmp);
        URLConnection urlConnection = url.openConnection();
        if (!(urlConnection instanceof HttpURLConnection)) throw new IllegalArgumentException();
        HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
        WeatherForecast result = new WeatherForecast();
        if (httpConn.getResponseCode() == 200) {
            InputStream is = httpConn.getInputStream();
            Gson json = new Gson();
            Type type = new TypeToken<WeatherForecast>() {
            }.getType();
             result = json.fromJson(new InputStreamReader(is), type);
        }
        return result;
    }
    //GETTING WEATHER BY LOCATION
    public static WeatherForecast getWeather(Location location) throws IOException {
        String tmp = String.format(WEATHER_ADDRESS_BYLOC, location.getLatitude(), location.getLongitude(), OWMTOKEN);
        System.out.println(tmp);
        URL url = new URL(tmp);
        URLConnection urlConnection = url.openConnection();
        if (!(urlConnection instanceof HttpURLConnection)) throw new IllegalArgumentException();
        HttpURLConnection httpConn = (HttpURLConnection) urlConnection;
        WeatherForecast result = new WeatherForecast();
        if (httpConn.getResponseCode() == 200) {
            InputStream is = httpConn.getInputStream();
            Gson json = new Gson();
            Type type = new TypeToken<WeatherForecast>() {
            }.getType();
            result = json.fromJson(new InputStreamReader(is), type);
        }
        return result;
    }

}
