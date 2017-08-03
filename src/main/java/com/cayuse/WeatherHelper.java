package com.cayuse;

public class WeatherHelper {

    public static class UrlBuilder {

        public static String getWeatherMapUrl(String zipcode) {
            return "http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&APPID=6581ca66d71dda19bdd5809073d78c5f";
        }

        public static String getTimeZoneUrl(Double latitude, Double longitude) {
            return "https://maps.googleapis.com/maps/api/timezone/json?location=" + latitude + "," + longitude + "&timestamp=1331161200&key=AIzaSyDA87hL8cmah_2BAtWZ5h9zXr4kSsZYTbM";
        }

        public static String getElevationUrl(Double latitude, Double longitude) {
            return "https://maps.googleapis.com/maps/api/elevation/json?locations=" + latitude + "," + longitude + "&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw";
        }
    }

}
