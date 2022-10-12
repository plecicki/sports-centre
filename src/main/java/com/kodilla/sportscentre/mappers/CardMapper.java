package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardMapper {

    public Card mapToCard(final CardDto cardDto) {
        return new Card(
                0L,
                cardDto.getUser(),
                cardDto.getAccessPass(),
                cardDto.getCardStatus()
        );
    }

    public CardDto mapToCardDto(final Card card) {
        return new CardDto(
                card.getUser(),
                card.getAccessPass(),
                card.getCardStatus()
        );
    }

    public List<CardDto> mapToCardDtoList(final List<Card> cardList) {
        return cardList.stream()
                .map(this::mapToCardDto)
                .collect(Collectors.toList());
    }
}
