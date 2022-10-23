package com.kodilla.sportscentre.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderCreateDto {

    private String description;
    private BigDecimal sum;
    private User user;
}
