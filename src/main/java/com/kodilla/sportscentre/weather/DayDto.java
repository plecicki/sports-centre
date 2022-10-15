package com.kodilla.sportscentre.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class DayDto {
    private LocalDate datetime;
    private Double temp;
    private Double humidity;
    private LocalTime sunrise;
    private LocalTime sunset;
    private String icon;
    private String description;
}
