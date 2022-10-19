package com.kodilla.sportscentre.config;

import com.kodilla.sportscentre.weather.WeatherConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherConfigTest {

    @Autowired
    WeatherConfig weatherConfig;

    @Value("${weather.api.endpoint}")
    private String weatherEndpoint;

    @Value("${weather.place}")
    private String weatherPlace;

    @Value("${weather.key}")
    private String weatherKey;

    @Test
    void weatherConfigTest() {
        //Given & When & Then
        Assertions.assertEquals(weatherEndpoint, weatherConfig.getWeatherEndpoint());
        Assertions.assertEquals(weatherPlace, weatherConfig.getWeatherPlace());
        Assertions.assertEquals(weatherKey, weatherConfig.getWeatherKey());
    }
}
