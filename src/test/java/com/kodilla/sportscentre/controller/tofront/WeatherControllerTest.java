package com.kodilla.sportscentre.controller.tofront;

import com.kodilla.sportscentre.controllers.tofront.WeatherController;
import com.kodilla.sportscentre.services.WeatherService;
import com.kodilla.sportscentre.weather.TomWeatherDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void shouldFetchWeatherTomorrowForecast() throws Exception {
        TomWeatherDto tomWeatherDto = new TomWeatherDto(
                "Wojcieszków, Woj. Lubelskie, Polska",
                LocalDate.of(2022, 10, 20),
                4.9,
                83.9,
                LocalTime.of(7, 3, 26),
                LocalTime.of(17, 26, 50),
                "partly-cloudy-day",
                "Partly cloudy throughout the day."
        );

        when(weatherService.getWeather()).thenReturn(tomWeatherDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/weather")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.resolvedAddress", Matchers.is("Wojcieszków, Woj. Lubelskie, Polska")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.datetime", Matchers.is("2022-10-20")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temp", Matchers.is(4.9)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity", Matchers.is(83.9)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sunrise", Matchers.is("07:03:26")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sunset", Matchers.is("17:26:50")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.icon", Matchers.is("partly-cloudy-day")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Partly cloudy throughout the day.")));
    }
}
