package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardDto;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.mappers.CardMapper;
import com.kodilla.sportscentre.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public List<CardDto> getAllCards() {
        List<Card> cardList = cardRepository.findAll();
        return cardMapper.mapToCardDtoList(cardList);
    }

    public CardDto getCardById(final Long cardId) throws CardNotFoundException {
        Card card = cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        return cardMapper.mapToCardDto(card);
    }

    public CardDto saveCard(final Card card) {
        return cardMapper.mapToCardDto(cardRepository.save(card));
    }

    public void deleteCard(final Long cardId) throws CardNotFoundException {
        cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
        cardRepository.deleteById(cardId);
    }
}
