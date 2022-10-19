package com.kodilla.sportscentre.controllers.tofront;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controllers.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.domain.*;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.services.UserCardService;
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

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(UserCardContr.class)
public class UserCardContrTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCardService userCardService;

    @Test
    void shouldEditAndReturnOldAndNewUserVersion() throws Exception {
        User user1 = new User(1L,
                "nameOld", "surnameOld",
                LocalDate.of(1990, 12, 12),
                "emailOld",
                "phoneOld",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.now().plusMonths(1)
        );
        User user2 = new User(1L,
                "nameNew", "surnameNew",
                LocalDate.of(1990, 12, 12),
                "emailNew",
                "phoneNew",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.now().plusMonths(1)
        );
        UserEditDto userEditDto = new UserEditDto(1L,
                "nameNew", "surnameNew",
                LocalDate.of(1990, 12, 12),
                "emailNew",
                "phoneNew",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                LocalDate.now().plusMonths(1)
        );
        UserOldNew userOldNew = new UserOldNew(user1, user2);

        when(userCardService.editUser(userEditDto)).thenReturn(userOldNew);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(userEditDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/usercard")
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
                        .delete("/v1/usercard/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateNewUser() throws Exception {

        User user = new User(1L,
                "nameNew", "surnameNew",
                LocalDate.of(1990, 12, 12),
                "emailNew",
                "phoneNew",
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
                "nameNew", "surnameNew",
                LocalDate.of(1990, 12, 12),
                "emailNew",
                "phoneNew",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                LocalDate.now().plusMonths(1)
        );

        when(userCardService.createUser(userCreateDto)).thenReturn(user);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(userCreateDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/usercard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
