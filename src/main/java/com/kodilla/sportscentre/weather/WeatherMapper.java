package com.kodilla.sportscentre.weather;

import org.springframework.stereotype.Service;

@Service
public class WeatherMapper {

    public TomWeatherDto mapWeatherDtoToTomWeatherDto(WeatherDto weatherDto) {
        return new TomWeatherDto(
                weatherDto.getResolvedAddress(),
                weatherDto.getDays()[1].getDatetime(),
                weatherDto.getDays()[1].getTemp(),
                weatherDto.getDays()[1].getHumidity(),
                weatherDto.getDays()[1].getSunrise(),
                weatherDto.getDays()[1].getSunset(),
                weatherDto.getDays()[1].getIcon(),
                weatherDto.getDays()[1].getDescription()
        );
    }
}
