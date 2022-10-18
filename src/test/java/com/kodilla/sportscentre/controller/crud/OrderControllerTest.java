package com.kodilla.sportscentre.controller.crud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controller.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.controllers.crud.OrderController;
import com.kodilla.sportscentre.domain.Order;
import com.kodilla.sportscentre.domain.OrderCreateDto;
import com.kodilla.sportscentre.domain.OrderEditDto;
import com.kodilla.sportscentre.services.OrderService;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldFetchListWithOrders() throws Exception {
        //Given
        List<Order> orderList = Arrays.asList(
                new Order(1L, "Description1", BigDecimal.valueOf(100.0)),
                new Order(2L, "Description2", BigDecimal.valueOf(100.0)),
                new Order(3L, "Description3", BigDecimal.valueOf(100.0))
        );

        when(orderService.getAllOrders()).thenReturn(orderList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].orderId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("Description1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sum", Matchers.is(100.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].orderId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("Description2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sum", Matchers.is(100.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].orderId", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].description", Matchers.is("Description3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].sum", Matchers.is(100.0)));
    }

    @Test
    void shouldFetchOrder() throws Exception {
        //Given
        Order order = new Order(1L, "Description1", BigDecimal.valueOf(100.0));

        when(orderService.getOrderById(1L)).thenReturn(order);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/order/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Description1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sum", Matchers.is(100.0)));
    }

    @Test
    void shouldCreateOrder() throws Exception {
        //Given
        Order order = new Order(1L, "Description1", BigDecimal.valueOf(100.0));
        OrderCreateDto orderCreateDto = new OrderCreateDto("Description1", BigDecimal.valueOf(100.0));

        when(orderService.createOrder(orderCreateDto)).thenReturn(order);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(orderCreateDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateOrder() throws Exception {
        //Given
        Order order = new Order(1L, "Description1", BigDecimal.valueOf(100.0));
        OrderEditDto orderEditDto = new OrderEditDto(1L, "Description1", BigDecimal.valueOf(100.0));

        when(orderService.editOrder(orderEditDto)).thenReturn(order);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(orderEditDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteOrder() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/order/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
