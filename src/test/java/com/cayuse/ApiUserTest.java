package com.cayuse;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ApiUserTest {

    @Test
    public void testUseOpenWeatherMapApi() throws Exception {
        //  create mock
        ApiUser apiUser = mock(ApiUser.class);

        //testURL
        String testUrl = "http://api.openweathermap.org/data/2.5/weather?zip=97202,us&APPID=6581ca66d71dda19bdd5809073d78c5f";

        //test String returned from callURL()
        String testJson = "{\"coord\":{\"lon\":-122.64,\"lat\":45.48},\"weather\":[{\"id\":711,\"main\":\"Smoke\",\"description\":\"smoke\",\"icon\":\"50d\"},{\"id\":721,\"main\":\"Haze\",\"description\":\"haze\",\"icon\":\"50d\"}],\"base\":\"stations\",\"main\":{\"temp\":291.14,\"pressure\":1015,\"humidity\":72,\"temp_min\":289.15,\"temp_max\":293.15},\"visibility\":9656,\"wind\":{\"speed\":0.81,\"deg\":15.5004},\"clouds\":{\"all\":1},\"dt\":1501681980,\"sys\":{\"type\":1,\"id\":2274,\"message\":0.0039,\"country\":\"US\",\"sunrise\":1501678612,\"sunset\":1501731350},\"id\":0,\"name\":\"Portland\",\"cod\":200}";

        //??
        City city = new City();

        // define return value for method getUniqueId()
     //   when(apiUser.callURL(Mockito.any(String.class))).thenReturn(testJson);


        // use mock in test....
        //assertEquals(apiUser.useOpenWeatherMapApi(city), 43);
    }



}
