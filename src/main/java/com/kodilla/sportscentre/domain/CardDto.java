package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.Enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardDto {
    private User user;
    private String accessPass;
    private CardStatus cardStatus;
}
