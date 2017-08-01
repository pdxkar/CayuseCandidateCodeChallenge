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
        System.out.println("City name is " + city.getCityName() + ".");
        System.out.println("Temperature is currently " + Math.round(city.getTemperature()) + " degrees Fahrenheit.");
        System.out.println("Time Zone name is " + city.getTimeZoneName() + ".");
        System.out.println("Elevation is " + Math.round(city.getElevation()) + ".");
        System.out.println("");
        System.out.println("*********************************************");

    }

}
