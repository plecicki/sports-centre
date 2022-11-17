package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardCreateDto;
import com.kodilla.sportscentre.domain.CardEditDto;
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

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(final Long cardId) throws CardNotFoundException {
        return cardRepository.findById(cardId).orElseThrow(CardNotFoundException::new);
    }

    public Card createCard(final CardCreateDto cardCreateDto) {
        Card card = cardMapper.mapToCardFromCreate(cardCreateDto);
        return cardRepository.save(card);
    }

    public Card editCard(final CardEditDto cardEditDto) {
        Card card = cardMapper.mapToCardFromEdit(cardEditDto);
        return cardRepository.save(card);
    }

    public void deleteCard(final Long cardId) throws CardNotFoundException {
        if(!cardRepository.existsById(cardId)) throw new CardNotFoundException();
        cardRepository.deleteById(cardId);
    }
}
