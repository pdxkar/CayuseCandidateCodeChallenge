package com.cayuse;

import java.io.Console;

public class WeatherChallenge {

	public static void main(String[] args) {

        City city = new City();
        ApiUser apiUser = new ApiUser();

		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}

        String zipCode = c.readLine("Enter a zipcode: ");

        city.setZipcode(zipCode);

		//weatherMap - city, latitude, longitude
        city = apiUser.useOpenWeatherMapApi(city);

        //GoogleTimeZone - current Time Zone
        city = apiUser.useGoogleTimeZoneApi(city);

        //Google Elevation - general elevation data
        city = apiUser.useGoogleElevationApi(city);

        //Display outcomes
        displayCityDetails(city);

	}

    public static void displayCityDetails(City city){

        System.out.println("*********************************************");
        System.out.println("");
        System.out.println("You entered zipcode " + city.getZipcode() + ".");
        System.out.println(city.getZipcode() + " is located in the city of " + city.getCityName() + ".");
        System.out.println("The temperature in " + city.getCityName() + " is currently " + Math.round(city.getTemperature()) + " degrees Fahrenheit.");
        System.out.println("The time zone of " + city.getCityName() + " is " + city.getTimeZoneName() + ".");
        System.out.println("The elevation of " + city.getCityName() + " is " + Math.round(city.getElevation()) + " meters above sea level.");
        System.out.println("");
        System.out.println("*********************************************");

    }

    public static String thisIsATest(){
        return "This is a test";
    }

}
