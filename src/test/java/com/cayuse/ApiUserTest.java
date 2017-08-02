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

        //??
        City city = new City();

        // define return value for method getUniqueId()
        //when(apiUser.callURL(Mockito.any(String.class))).thenReturn(testJson);


        // use mock in test....
        assertEquals(apiUser.useOpenWeatherMapApi(city), 43);
    }



}
