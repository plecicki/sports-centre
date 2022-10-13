package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.Goals;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserCreateDto {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private Goals goal;
    private boolean student;
    private boolean gym;
    private boolean swimmingPool;
    private Card card;
}
