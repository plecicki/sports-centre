package com.kodilla.sportscentre.weather;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WeatherConfig {

    @Value("${weather.api.endpoint}")
    private String weatherEndpoint;

    @Value("${weather.place}")
    private String weatherPlace;

    @Value("${weather.key}")
    private String weatherKey;
}
