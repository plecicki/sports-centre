package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.services.WeatherService;
import com.kodilla.sportscentre.weather.TomWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/weather")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<TomWeatherDto> getWeather() {
        return ResponseEntity.ok(weatherService.getWeather());
    }
}
