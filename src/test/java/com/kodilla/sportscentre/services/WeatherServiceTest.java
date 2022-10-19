package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.weather.TomWeatherDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    void getWeatherTest() {
        //Given & When
        TomWeatherDto tomWeatherDto = weatherService.getWeather();

        //Then
        Assertions.assertNotNull(tomWeatherDto.getResolvedAddress());
        Assertions.assertNotNull(tomWeatherDto.getDatetime());
        Assertions.assertNotNull(tomWeatherDto.getTemp());
        Assertions.assertNotNull(tomWeatherDto.getHumidity());
        Assertions.assertNotNull(tomWeatherDto.getSunrise());
        Assertions.assertNotNull(tomWeatherDto.getSunset());
        Assertions.assertNotNull(tomWeatherDto.getIcon());
        Assertions.assertNotNull(tomWeatherDto.getDescription());
    }
}
