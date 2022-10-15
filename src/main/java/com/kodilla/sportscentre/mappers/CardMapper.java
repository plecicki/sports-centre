package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardMapper {

    public Card mapToCardFromCreate(final CardCreateDto cardCreateDto) {
        return new Card(
                0L,
                cardCreateDto.getUser(),
                cardCreateDto.getAccessPass(),
                cardCreateDto.getCardStatus()
        );
    }

    public Card mapToCardFromEdit(final CardEditDto cardEditDto) {
        return new Card(
                cardEditDto.getCardId(),
                cardEditDto.getUser(),
                cardEditDto.getAccessPass(),
                cardEditDto.getCardStatus()
        );
    }

    public CardCreateDto mapToCardCreateDto(final Card card) {
        return new CardCreateDto(
                card.getUser(),
                card.getAccessPass(),
                card.getCardStatus()
        );
    }

    public CardEditDto mapToCardEditDto(final Card card) {
        return new CardEditDto(
                card.getCardId(),
                card.getUser(),
                card.getAccessPass(),
                card.getCardStatus()
        );
    }

    public List<CardCreateDto> mapToCardCreateDtoList(final List<Card> cardList) {
        return cardList.stream()
                .map(this::mapToCardCreateDto)
                .collect(Collectors.toList());
    }

    public List<CardEditDto> mapToCardEditDtoList(final List<Card> cardList) {
        return cardList.stream()
                .map(this::mapToCardEditDto)
                .collect(Collectors.toList());
    }

    public Card mapFromCTCloneToCard(final CardToClone cardToClone) {
        return new Card(
                cardToClone.getCardId(),
                cardToClone.getUser(),
                cardToClone.getAccessPass(),
                cardToClone.getCardStatus()
        );
    }

    public CardToClone mapFromCardToCTClone(final Card card) {
        return new CardToClone.CardToCloneBuilder()
                .cardId(card.getCardId())
                .user(card.getUser())
                .accessPass(card.getAccessPass())
                .cardStatus(card.getCardStatus())
                .build();
    }
}
