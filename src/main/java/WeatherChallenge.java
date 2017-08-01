import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherChallenge {

	public static void main(String[] args) {

        City city = new City();

		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}

		//weatherMap - city, latitude, longitude
        city = useOpenWeatherMapApi(c, city);

        //GoogleTimeZone - current Time Zone
        city = useGoogleTimeZoneApi(city);

        //Google Elevation - general elevation data
        city = useGoogleElevationApi(city);

        //Display outcomes
        displayCityDetails(city);

	}

    public static City useOpenWeatherMapApi(Console c, City city){

        JSONObject coordinates;
        Double latitude = null;
        Double longitude = null;
        String cityName = null;

        String zipCode = c.readLine("Enter a zipcode: ");

        city.setZipcode(zipCode);

        String weatherMapUrl = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&APPID=6581ca66d71dda19bdd5809073d78c5f";

        //WeatherMap (provides City name, Latitude, Longitude)
        String weatherMapString = callURL(weatherMapUrl);

        JSONParser wMapParser = new JSONParser();
        JSONObject weatherMapJson = null;
        try {
            weatherMapJson = (JSONObject) wMapParser.parse(weatherMapString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            coordinates = (JSONObject) weatherMapJson.get("coord");
            latitude = (Double) coordinates.get("lat");
            longitude = (Double) coordinates.get("lon");
            cityName = (String) weatherMapJson.get("name");

        } catch (Exception e){
            e.printStackTrace();
        }

        city.setCityName(cityName);
        city.setLatitude(latitude);
        city.setLongitude(longitude);

        return city;

    }

    public static City useGoogleTimeZoneApi(City city){

        String timeZoneName = null;

        String timeZoneUrl = "https://maps.googleapis.com/maps/api/timezone/json?location=" + city.getLatitude() + "," + city.getLongitude() + "&timestamp=1331161200&key=AIzaSyDA87hL8cmah_2BAtWZ5h9zXr4kSsZYTbM";

        String googleTimeZoneString = callURL(timeZoneUrl);

        JSONParser gTzParser = new JSONParser();
        JSONObject googleTimeZoneJson = null;
        try {
            googleTimeZoneJson = (JSONObject) gTzParser.parse(googleTimeZoneString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            timeZoneName = (String) googleTimeZoneJson.get("timeZoneName");
        } catch (Exception e ){
            e.printStackTrace();
        }

        city.setTimeZoneName(timeZoneName);

        return city;

    }

    public static City useGoogleElevationApi(City city){

        String googleElevationUrl = "https://maps.googleapis.com/maps/api/elevation/json?locations=" + city.getLatitude() + "," + city.getLongitude() + "&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw";
        String googleElevationString = callURL(googleElevationUrl);
        JSONParser gElevParser = new JSONParser();
        JSONObject googleElevJson;
        JSONObject myJsonObject = null;

        try {
            googleElevJson = (JSONObject) gElevParser.parse(googleElevationString);
            JSONArray array = (JSONArray) googleElevJson.get("results");
            myJsonObject = (JSONObject) array.get(0);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        city.setElevation((Double) myJsonObject.get("elevation"));

        return city;
    }

    public static void displayCityDetails(City city){

        System.out.println("*********************************************");
        System.out.println("");
        System.out.println("You entered zipcode " + city.getZipcode());
        System.out.println("City name is " + city.getCityName());
        System.out.println("Longitude is " + city.getLongitude());
        System.out.println("Latitude is " + city.getLatitude());
        System.out.println("Time Zone name is " + city.getTimeZoneName());
        System.out.println("Elevation is " + city.getElevation());
        System.out.println("");
        System.out.println("*********************************************");

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

}
