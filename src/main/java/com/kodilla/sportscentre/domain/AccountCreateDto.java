package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountCreateDto {

    private String username;
    private String password;
    private Role role;
    private User user;

    private String createAdminKey;
}
