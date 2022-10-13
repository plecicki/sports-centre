package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardCreateDto {
    private User user;
    private String accessPass;
    private CardStatus cardStatus;
}
