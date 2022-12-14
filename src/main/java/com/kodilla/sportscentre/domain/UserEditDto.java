package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.Goals;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserEditDto {
    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private Goals goal;
    private Boolean student;
    private Boolean gym;
    private Boolean swimmingPool;
    private Card card;
    private Boolean autoExtension;
    private LocalDate subValidity;
}
