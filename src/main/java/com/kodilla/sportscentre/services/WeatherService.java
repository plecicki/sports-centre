package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.weather.TomWeatherDto;
import com.kodilla.sportscentre.weather.WeatherClient;
import com.kodilla.sportscentre.weather.WeatherDto;
import com.kodilla.sportscentre.mappers.WeatherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherMapper weatherMapper;

    public TomWeatherDto getWeather() {
        WeatherDto weatherDto = weatherClient.getWeather();
        return weatherMapper.mapWeatherDtoToTomWeatherDto(weatherDto);
    }
}
