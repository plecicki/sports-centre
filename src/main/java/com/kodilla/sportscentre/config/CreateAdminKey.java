package com.kodilla.sportscentre.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CreateAdminKey {

    @Value("${create.admin.key}")
    private String createAdminKey;
}
