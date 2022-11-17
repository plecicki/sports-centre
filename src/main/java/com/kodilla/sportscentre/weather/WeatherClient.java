package com.kodilla.sportscentre.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;

    public WeatherDto getWeather() {
        URI url = getWeatherUrlAddress();
        return restTemplate.getForObject(url, WeatherDto.class);
    }

    private URI getWeatherUrlAddress() {
        return UriComponentsBuilder.fromHttpUrl(weatherConfig.getWeatherEndpoint() + "/" + weatherConfig.getWeatherPlace())
                .queryParam("unitGroup", "metric")
                .queryParam("include", "days")
                .queryParam("key", weatherConfig.getWeatherKey())
                .queryParam("contentType", "json")
                .build()
                .encode()
                .toUri();
    }
}
