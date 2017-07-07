import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//public class WeatherChallenge {
//	public static void main(String[] args) {
//		System.out.println("Enter a zipcode: ");
//		String zipCode = args[0];
//		System.out.println("You entered: " + zipCode + "!");
////        for (String s: args) {
////            System.out.println(s);
////        }
////        System.out.println("Helloxxx World!"); // Display the string.
//    }
//}
 
public class WeatherChallenge {
 
	public static void main(String[] args) {
		System.out.println("Enter a zipcode: ");
		String zipCode = args[0];
		System.out.println("You entered: " + zipCode + "!");
		
		System.out.println("\nOutput: \n" + callURL("http://api.openweathermap.org/data/2.5/weather?zip=32003,us&APPID=6581ca66d71dda19bdd5809073d78c5f"));

		//WeatherMap (City, Latitude, Longitude)
		String weatherMapString = callURL("http://api.openweathermap.org/data/2.5/weather?zip=32003,us&APPID=6581ca66d71dda19bdd5809073d78c5f");
		
		JSONParser wMapParser = new JSONParser(); 
		JSONObject weatherMapJson = null;
		try {
			weatherMapJson = (JSONObject) wMapParser.parse(weatherMapString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//GoogleTimeZone - current time zone

		String googleTimeZoneString = callURL("https://maps.googleapis.com/maps/api/timezone/json?location=30.09,-81.72&timestamp=1331161200&key=AIzaSyDA87hL8cmah_2BAtWZ5h9zXr4kSsZYTbM");
		
	//	System.out.println("\nOutput: \n" + callURL("https://maps.googleapis.com/maps/api/timezone/json?location=30.09,-81.72&timestamp=1331161200&key=AIzaSyDA87hL8cmah_2BAtWZ5h9zXr4kSsZYTbM"));
		
		JSONParser gTzParser = new JSONParser(); 
		JSONObject googleTimeZoneJson = null;
		try {
			googleTimeZoneJson = (JSONObject) gTzParser.parse(googleTimeZoneString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Google Elevation - general elevation data
//		Project Name = Katherine-Ennis
//		AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw
//		by lat and long:
//		https://maps.googleapis.com/maps/api/elevation/json?locations=30.09,-81.72&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw
		
		String googleElevationString = callURL("https://maps.googleapis.com/maps/api/elevation/json?locations=30.09,-81.72&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw");
		
		System.out.println("\nOutput: \n" + callURL("https://maps.googleapis.com/maps/api/elevation/json?locations=30.09,-81.72&key=AIzaSyBBJpZIM_9_r_7Ntxno4A-8MZx8nici-gw"));
		
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
		System.out.println("Requeted URL:" + myURL);
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
	
	public static void deserializeWeatherMapJson(JSONObject wpObj, JSONObject gtzObj, JSONObject gElevObj ){
	
		System.out.println("City name is ");
		System.out.println(wpObj.get("name"));
		
		JSONObject coordinates = (JSONObject) wpObj.get("coord");		
		
		System.out.println("Longitude is ");
		System.out.println(coordinates.get("lon"));
		
		System.out.println("Latitude is ");
		System.out.println(coordinates.get("lat"));
		
		System.out.println("Current timezone is ");
		System.out.println(gtzObj.get("timeZoneName"));
		
		JSONObject results = (JSONObject) gElevObj.get("results");	
		
		System.out.println("REsults is ");
		System.out.println(results);

		
		System.out.println("Elevation is ");
		System.out.println(results.get("elevation"));
		


	}
}
