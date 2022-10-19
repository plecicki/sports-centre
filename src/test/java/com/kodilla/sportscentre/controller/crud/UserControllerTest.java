package com.kodilla.sportscentre.controller.crud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controller.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.controllers.crud.UserController;
import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserCreateDto;
import com.kodilla.sportscentre.domain.UserEditDto;
import com.kodilla.sportscentre.domain.enums.CardStatus;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.services.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldFetchListWithUsers() throws Exception {
        //Given
        User user1 = new User(1L,
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
                LocalDate.of(2022, 11, 18)
        );
        User user2 = new User(2L,
                "name2", "surname2",
                LocalDate.of(1990, 12, 12),
                "email2",
                "phone2",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        User user3 = new User(3L,
                "name3", "surname3",
                LocalDate.of(1990, 12, 12),
                "email3",
                "phone3",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        Card card = new Card(1L, user2, "accessPass1", CardStatus.AVAILABLE);
        user2.setCard(card);
        List<User> userList = Arrays.asList(user1, user2, user3);

        when(userService.getAllUsers()).thenReturn(userList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", Matchers.is("surname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthDate", Matchers.is("1990-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("email1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone", Matchers.is("phone1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].goal", Matchers.is("HEALTH")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].student", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gym", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].swimmingPool", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].card", Matchers.is(Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].autoExtension", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].invoices", Matchers.is(Matchers.empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subValidity", Matchers.is("2022-11-18")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("name2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].surname", Matchers.is("surname2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].birthDate", Matchers.is("1990-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("email2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phone", Matchers.is("phone2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].goal", Matchers.is("HEALTH")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].student", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].gym", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].swimmingPool", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].card.cardId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].autoExtension", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].invoices", Matchers.is(Matchers.empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].subValidity", Matchers.is("2022-11-18")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].userId", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("name3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].surname", Matchers.is("surname3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].birthDate", Matchers.is("1990-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].email", Matchers.is("email3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].phone", Matchers.is("phone3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].goal", Matchers.is("HEALTH")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].student", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].gym", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].swimmingPool", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].card", Matchers.is(Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].autoExtension", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].invoices", Matchers.is(Matchers.empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].subValidity", Matchers.is("2022-11-18")));
    }

    @Test
    void shouldFetchUser() throws Exception {
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
                LocalDate.of(2022, 11, 18)
        );

        when(userService.getUserById(1L)).thenReturn(user);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("surname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", Matchers.is("1990-12-12")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", Matchers.is("phone1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.goal", Matchers.is("HEALTH")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.student", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gym", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.swimmingPool", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.card", Matchers.is(Matchers.nullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.autoExtension", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoices", Matchers.is(Matchers.empty())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subValidity", Matchers.is("2022-11-18")));
    }

    @Test
    void shouldCreateUser() throws Exception {
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
        UserCreateDto userCreateDto = new UserCreateDto(
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
                LocalDate.now().plusMonths(1)
        );

        when(userService.createUser(userCreateDto)).thenReturn(user);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(userCreateDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        //Given
        User user = new User(1L,
                "name1", "surname1",
                null,
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                null
        );
        UserEditDto userEditDto = new UserEditDto(
                1L,
                "name1", "surname1",
                null,
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                null
        );

        when(userService.editUser(userEditDto)).thenReturn(user);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(userEditDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
