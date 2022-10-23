package com.kodilla.sportscentre.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderEditDto {

    private Long orderId;
    private String description;
    private BigDecimal sum;
    private User user;
}
