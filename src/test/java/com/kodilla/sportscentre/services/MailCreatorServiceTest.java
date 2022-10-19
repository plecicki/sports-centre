package com.kodilla.sportscentre.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailCreatorServiceTest {

    @Autowired
    private MailCreatorService mailCreatorService;

    @Test
    void shouldFetchBuiltEmail() {
        //Given & When
        String mail = mailCreatorService.buildCardEmail("Test Name", "Test Message");
        System.out.println(mail.indexOf("Test Message"));

        //Then
        Assertions.assertTrue(mail.contains("Test Name"));
        Assertions.assertTrue(mail.contains("Test Message"));
    }
}
