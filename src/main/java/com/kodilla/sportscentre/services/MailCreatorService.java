package com.kodilla.sportscentre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildCardEmail(String name, String message) {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("message", message);
        return templateEngine.process("index", context);
    }
}
