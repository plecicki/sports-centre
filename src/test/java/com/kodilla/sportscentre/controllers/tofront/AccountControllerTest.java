package com.kodilla.sportscentre.controllers.tofront;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controllers.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.domain.AccountCreateDto;
import com.kodilla.sportscentre.domain.AccountInDto;
import com.kodilla.sportscentre.domain.AccountOutDto;
import com.kodilla.sportscentre.domain.enums.Role;
import com.kodilla.sportscentre.services.AccountService;
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

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void shouldCreateAccount() throws Exception {

        //Given
        AccountCreateDto accountCreateDto = new AccountCreateDto(
                "username",
                "password",
                Role.USER,
                null,
                null
        );

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(accountCreateDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldLogin() throws Exception {

        //Given
        AccountInDto accountInDto = new AccountInDto(
                "username",
                "password"
        );

        AccountOutDto accountOutDto = new AccountOutDto(
                "username",
                Role.USER,
                null
        );

        when(accountService.login(accountInDto)).thenReturn(accountOutDto);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        String jsonContent = gson.toJson(accountInDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCheckIfExistsByUsername() throws Exception {
        //Given
        when(accountService.existsUserByUsername("username")).thenReturn(true);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/account/username")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)));
    }
}
