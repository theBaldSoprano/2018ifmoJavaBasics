package com.study.itmo.gregory.finalTasks.osmtask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.List;

public class OpenStreetMapProcessor {
    public static final String URL_PATTERN =
            "http://nominatim.openstreetmap.org/search?street=%s&format=json&city=СПб";

    public static String getSearchRequest() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static URL getUrl(String searchRequest) throws MalformedURLException, UnsupportedEncodingException {
        return new URL(String.format(URL_PATTERN, URLEncoder.encode(searchRequest, "UTF-8")));
    }

    public static List<Place> processUrl(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();

        //todo tcp google need? to disconnect connection
        if (!(urlConnection instanceof HttpURLConnection)) throw new IllegalArgumentException();
        HttpURLConnection httpConn = (HttpURLConnection)urlConnection;
        List<Place> places = null;
        if(httpConn.getResponseCode() == 200) {
            InputStream is = httpConn.getInputStream();
            Gson json = new Gson();
            Type type = new TypeToken<List<Place>>(){}.getType();
            places = json.fromJson(new InputStreamReader(is), type);
        }
        return places;

    }


}
