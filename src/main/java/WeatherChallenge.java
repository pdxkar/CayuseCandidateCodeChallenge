import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherChallenge {

	public static void main(String[] args) {

		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}

		String zipCode = c.readLine("Enter a zipcode: ");

		//WeatherMap (City, Latitude, Longitude)
		String weatherMapString = callURL("http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + ",us&APPID=6581ca66d71dda19bdd5809073d78c5f");

		JSONParser wMapParser = new JSONParser();
		JSONObject weatherMapJson = null;
		try {
			weatherMapJson = (JSONObject) wMapParser.parse(weatherMapString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//GoogleTimeZone - current time zone
		//String googleTimeZoneString = callURL("https://maps.googleapis.com/maps/api/timezone/json?location=30.09,-81.72&timestamp=1331161200&key=AIzaSyDA87hL8cmah_2BAtWZ5h9zXr4kSsZYTbM");

        String googleTimeZoneString = getTimeZoneUrl(weatherMapJson);

		JSONParser gTzParser = new JSONParser();
		JSONObject googleTimeZoneJson = null;
		try {
			googleTimeZoneJson = (JSONObject) gTzParser.parse(googleTimeZoneString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


//		Google Elevation - general elevation data
		String googleElevationString = callURL("https://maps.googleapis.com/maps/api/elevation/json?locations=30.09,-81.72&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw");

		System.out.println("\nElevation Output: \n" + callURL("https://maps.googleapis.com/maps/api/elevation/json?locations=30.09,-81.72&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw"));

		JSONParser gElevParser = new JSONParser();
		JSONObject googleElevJson = null;
		try {
			googleElevJson = (JSONObject) gElevParser.parse(googleTimeZoneString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		deserializeWeatherMapJson(weatherMapJson, googleTimeZoneJson, googleElevJson);

	}

	public static String callURL(String myURL) {
		//System.out.println("Requested URL:" + myURL);
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

	public static String getTimeZoneUrl (JSONObject jsonObject){
        JSONObject coordinates = (JSONObject) jsonObject.get("coord");

        String latitude = (String) coordinates.get("lat");
        String longitude = (String) coordinates.get("lat");

        System.out.println("Longitude is " + longitude);
        System.out.println("Latitude is " + latitude);

        //location: a comma-separated lat,lng tuple (eg. location=-33.86,151.20), representing the location to look up
        String timeZoneUrl = "https://maps.googleapis.com/maps/api/timezone/json?location=" +
                latitude + "," + longitude + "&timestamp=1331161200&key=AIzaSyDA87hL8cmah_2BAtWZ5h9zXr4kSsZYTbM";

        return timeZoneUrl;
    }

	public static void deserializeWeatherMapJson(JSONObject wpObj, JSONObject gtzObj, JSONObject gElevObj ){

		JSONObject coordinates = (JSONObject) wpObj.get("coord");

		System.out.println("City name is " + wpObj.get("name"));
		System.out.println("Longitude is " + coordinates.get("lon"));
		System.out.println("Latitude is " + coordinates.get("lat"));
		System.out.println("Current timezone is " + gtzObj.get("timeZoneName"));

		JSONObject parent = gElevObj;
		JSONArray results = (JSONArray) gElevObj.get("results");


		//TODO

//		for (Object result : results) {
//		    System.out.println("Elevation is   " + ((JSONObject) result).get("elevation"));
//
//		}

	}
}
