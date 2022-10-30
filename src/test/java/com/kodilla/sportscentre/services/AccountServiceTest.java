package com.kodilla.sportscentre.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.sportscentre.controllers.serializer.LocalDateSerializer;
import com.kodilla.sportscentre.domain.Account;
import com.kodilla.sportscentre.domain.AccountCreateDto;
import com.kodilla.sportscentre.domain.AccountInDto;
import com.kodilla.sportscentre.domain.AccountOutDto;
import com.kodilla.sportscentre.domain.enums.Role;
import com.kodilla.sportscentre.repositories.AccountRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldCreateAccount() throws Exception {

        //Given
        AccountCreateDto accountCreateDto = new AccountCreateDto(
                "usernameTest123456789009876543212345678909876543211234567890987654321",
                "password",
                Role.USER,
                null,
                null
        );

        //When
        accountService.createAccount(accountCreateDto);

        //Then
        Account account = accountRepository.findByUsername("usernameTest123456789009876543212345678909876543211234567890987654321")
                .orElseThrow();
        Assertions.assertNotNull(account.getAccountId());

        //CleanUp
        accountRepository.delete(account);
    }

    @Test
    void shouldLogin() throws Exception{

        //Given
        AccountCreateDto accountCreateDto = new AccountCreateDto(
                "usernameTest123456789009876543212345678909876543211234567890987654321",
                "password",
                Role.USER,
                null,
                null
        );
        accountService.createAccount(accountCreateDto);

        //When
        AccountOutDto accountOutDto = accountService.login(new AccountInDto(
                "usernameTest123456789009876543212345678909876543211234567890987654321",
                "password"
        ));

        //Then
        Account account = accountRepository.findByUsername("usernameTest123456789009876543212345678909876543211234567890987654321")
                .orElseThrow();
        Assertions.assertNotNull(account.getAccountId());
        Assertions.assertEquals("usernameTest123456789009876543212345678909876543211234567890987654321",
                accountOutDto.getUsername());

        //CleanUp
        accountRepository.delete(account);


    }

    @Test
    void shouldCheckIfExistsByUsername() throws Exception {
        //Given
        AccountCreateDto accountCreateDto = new AccountCreateDto(
                "usernameTest123456789009876543212345678909876543211234567890987654321",
                "password",
                Role.USER,
                null,
                null
        );
        accountService.createAccount(accountCreateDto);

        //When & Then
        Assertions.assertTrue(
                accountService.existsUserByUsername("usernameTest123456789009876543212345678909876543211234567890987654321")
        );

        //CleanUp
        Account account = accountRepository.findByUsername("usernameTest123456789009876543212345678909876543211234567890987654321")
                .orElseThrow();
        accountRepository.delete(account);
    }
}
