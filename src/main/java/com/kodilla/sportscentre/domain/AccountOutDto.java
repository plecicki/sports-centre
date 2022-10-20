package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountOutDto {
    private String username;
    private Role role;
    private User user;
}
