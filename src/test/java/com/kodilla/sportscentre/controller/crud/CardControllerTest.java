package com.kodilla.sportscentre.controller.crud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controller.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.controllers.crud.CardController;
import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardCreateDto;
import com.kodilla.sportscentre.domain.CardEditDto;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.enums.CardStatus;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.services.CardService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @Test
    void shouldFetchListWithCards() throws Exception {
        //Given
        User user = new User(1L,
                "name1", "surname1",
                LocalDate.of(1990, 12, 12),
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.now().plusMonths(1)
                );
        List<Card> cardList = Arrays.asList(
                new Card(1L, null, "accessPass1", CardStatus.AVAILABLE),
                new Card(2L, user, "accessPass2", CardStatus.AVAILABLE),
                new Card(3L, null, "accessPass3", CardStatus.LOST)
        );
        user.setCard(cardList.get(1));

        when(cardService.getAllCards()).thenReturn(cardList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/card")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cardId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accessPass", Matchers.is("accessPass1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cardStatus", Matchers.is("AVAILABLE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cardId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].accessPass", Matchers.is("accessPass2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cardStatus", Matchers.is("AVAILABLE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].cardId", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].accessPass", Matchers.is("accessPass3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].cardStatus", Matchers.is("LOST")));
    }

    @Test
    void shouldFetchCard() throws Exception {
        //Given
        Card card = new Card(1L, null, "accessPass1", CardStatus.AVAILABLE);

        when(cardService.getCardById(1L)).thenReturn(card);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/card/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessPass", Matchers.is("accessPass1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cardStatus", Matchers.is("AVAILABLE")));
    }

    @Test
    void shouldCreateCard() throws Exception {
        //Given
        Card card = new Card(1L, null, "accessPass1", CardStatus.AVAILABLE);
        CardCreateDto cardCreateDto = new CardCreateDto(null, "accessPass1", CardStatus.AVAILABLE);

        when(cardService.createCard(cardCreateDto)).thenReturn(card);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(cardCreateDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateCard() throws Exception {
        Card card = new Card(1L, null, "accessPass1", CardStatus.AVAILABLE);
        CardEditDto cardEditDto = new CardEditDto(1L, null, "accessPass1", CardStatus.AVAILABLE);

        when(cardService.editCard(cardEditDto)).thenReturn(card);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(cardEditDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteCard() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/card/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
