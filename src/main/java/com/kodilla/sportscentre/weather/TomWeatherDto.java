package com.kodilla.sportscentre.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TomWeatherDto {
    private String resolvedAddress;
    private LocalDate datetime;
    private Double temp;
    private Double humidity;
    private LocalTime sunrise;
    private LocalTime sunset;
    private String icon;
    private String description;
}
