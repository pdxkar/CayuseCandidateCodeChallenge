package com.cayuse;

public class Apis {

    private String weatherMapUrl;
    //private String zipcode;


    //"http://api.openweathermap.org/data/2.5/weather?zip=" + city.getZipcode() + ",us&APPID=6581ca66d71dda19bdd5809073d78c5f"


    public String getWeatherMapUrl(String zipcode) {
        return "http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode + ",us&APPID=6581ca66d71dda19bdd5809073d78c5f";
       // return weatherMapUrl;
    }

    public void setWeatherMapUrl(String weatherMapUrl) {
        this.weatherMapUrl = weatherMapUrl;
    }

//    public String getZipcode() {
//        return zipcode;
//    }
//
//    public void setZipcode(String zipcode) {
//        this.zipcode = zipcode;
//    }
}
