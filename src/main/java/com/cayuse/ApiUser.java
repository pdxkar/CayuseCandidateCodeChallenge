package com.cayuse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class ApiUser {

   // String weatherMapUrl;

    public static City useOpenWeatherMapApi(City city){

        String weatherMapUrl = "http://api.openweathermap.org/data/2.5/weather?zip=" + city.getZipcode() + ",us&APPID=6581ca66d71dda19bdd5809073d78c5f";

        //WeatherMap (provides com.cayuse.City name, Latitude, Longitude)
        String weatherMapString = callURL(weatherMapUrl);


        JSONParser wMapParser = new JSONParser();

        try {
            JSONObject weatherMapJson = (JSONObject) wMapParser.parse(weatherMapString);
            JSONObject coordinates = (JSONObject) weatherMapJson.get("coord");
            JSONObject main = (JSONObject) weatherMapJson.get("main");
            city.setLatitude((Double) coordinates.get("lat"));
            city.setLongitude((Double) coordinates.get("lon"));
            city.setCityName((String) weatherMapJson.get("name"));
            //convert temperature from Kelvin to Fahrenheit
            //(K - 273.15) * 9/5 + 32
            city.setTemperature(((Double) main.get("temp") - 273.15) * 9/5 + 32);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return city;

    }

    public static City useGoogleTimeZoneApi(City city){

        String timeZoneUrl = "https://maps.googleapis.com/maps/api/timezone/json?location=" + city.getLatitude() + "," + city.getLongitude() + "&timestamp=1331161200&key=AIzaSyDA87hL8cmah_2BAtWZ5h9zXr4kSsZYTbM";

        String googleTimeZoneString = callURL(timeZoneUrl);

        JSONParser gTzParser = new JSONParser();
        JSONObject googleTimeZoneJson = null;
        try {
            googleTimeZoneJson = (JSONObject) gTzParser.parse(googleTimeZoneString);
            city.setTimeZoneName((String) googleTimeZoneJson.get("timeZoneName"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return city;

    }

    public static City useGoogleElevationApi(City city){

        String googleElevationUrl = "https://maps.googleapis.com/maps/api/elevation/json?locations=" + city.getLatitude() + "," + city.getLongitude() + "&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw";
        String googleElevationString = callURL(googleElevationUrl);
        JSONParser gElevParser = new JSONParser();

        try {
            JSONObject googleElevJson = (JSONObject) gElevParser.parse(googleElevationString);
            JSONArray resultsArray = (JSONArray) googleElevJson.get("results");
            city.setElevation((Double) ((JSONObject) resultsArray.get(0)).get("elevation"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return city;
    }

    public static String callURL(String myURL) {
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }

        return sb.toString();
    }

    //getters and setters

//    public String getWeatherMapUrl() {
//        return weatherMapUrl;
//    }
//
//    public void setWeatherMapUrl(City city) {
//        this.weatherMapUrl = "http://api.openweathermap.org/data/2.5/weather?zip=" + city.getZipcode() + ",us&APPID=6581ca66d71dda19bdd5809073d78c5f";
//    }
}
