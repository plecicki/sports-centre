package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    @Test
    void shouldSendEmail() {
        //Given
        Mail mail = new Mail(
                "Test mail",
                "Test subject",
                "Test subject",
                null,
                "Test name"
        );

        //When
        simpleEmailService.sendDaily(mail);

        //Then
        verify(javaMailSender).send(ArgumentMatchers.any(MimeMessagePreparator.class));
    }
}
