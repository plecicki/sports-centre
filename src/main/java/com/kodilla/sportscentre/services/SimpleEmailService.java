package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void sendDaily(final Mail mail) {
        try {
            javaMailSender.send(createMimeDailyMessage(mail));
        } catch (MailException exception) {
            log.error("Failed to process email sending: " + exception.getMessage(), exception);
        }
    }

    private MimeMessagePreparator createMimeDailyMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mailCreatorService.buildCardEmail(mail.getName(), mail.getMessage()), true);
        };
    }
}
