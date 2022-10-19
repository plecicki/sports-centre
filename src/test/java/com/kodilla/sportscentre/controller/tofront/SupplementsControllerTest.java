package com.kodilla.sportscentre.controller.tofront;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controller.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.controllers.tofront.SupplementsController;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderDecInDto;
import com.kodilla.sportscentre.services.SupplementsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(SupplementsController.class)
public class SupplementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplementsService supplementsService;

    @Test
    void shouldCreateOrder() throws Exception{
        OrderDecInDto orderDecInDto = new OrderDecInDto(
                true,
                true,
                true,
                false,
                true
        );
        OrderCreateDto orderCreateDto = new OrderCreateDto(
                " BCAA Caffeine Citrulline Protein",
                BigDecimal.valueOf(148.0)
        );

        when(supplementsService.createOrder(orderDecInDto)).thenReturn(orderCreateDto);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(orderDecInDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/supplements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
