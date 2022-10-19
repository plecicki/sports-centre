package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardCreateDto;
import com.kodilla.sportscentre.domain.CardEditDto;
import com.kodilla.sportscentre.domain.enums.CardStatus;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.repositories.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardRepository cardRepository;

    @Test
    void shouldFetchListWithCards() {
        //Given
        CardCreateDto card1 = new CardCreateDto(null, "accessPass1", CardStatus.AVAILABLE);
        CardCreateDto card2 = new CardCreateDto(null, "accessPass2", CardStatus.AVAILABLE);
        CardCreateDto card3 = new CardCreateDto(null, "accessPass3", CardStatus.LOST);

        cardService.createCard(card1);
        cardService.createCard(card2);
        cardService.createCard(card3);

        //When
        List<Card> cards = cardService.getAllCards();

        //Then
        Assertions.assertNotNull(cards.get(cards.size()-3).getCardId());
        Assertions.assertNull(cards.get(cards.size()-3).getUser());
        Assertions.assertEquals("accessPass1", cards.get(cards.size()-3).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cards.get(cards.size()-3).getCardStatus());
        Assertions.assertNotNull(cards.get(cards.size()-2).getCardId());
        Assertions.assertNull(cards.get(cards.size()-2).getUser());
        Assertions.assertEquals("accessPass2", cards.get(cards.size()-2).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cards.get(cards.size()-2).getCardStatus());
        Assertions.assertNotNull(cards.get(cards.size()-1).getCardId());
        Assertions.assertNull(cards.get(cards.size()-1).getUser());
        Assertions.assertEquals("accessPass3", cards.get(cards.size()-1).getAccessPass());
        Assertions.assertEquals(CardStatus.LOST, cards.get(cards.size()-1).getCardStatus());

        //CleanUp
        cardRepository.delete(cards.get(cards.size()-3));
        cardRepository.delete(cards.get(cards.size()-2));
        cardRepository.delete(cards.get(cards.size()-1));
    }

    @Test
    void shouldFetchCard() {
        //Given
        CardCreateDto cardCreateDto = new CardCreateDto(null, "accessPass1", CardStatus.AVAILABLE);

        Card card = cardService.createCard(cardCreateDto);

        //When
        Card cardFromDB = new Card();
        try {
            cardFromDB = cardService.getCardById(card.getCardId());
        } catch (CardNotFoundException e) {
            System.out.println(e);
        }

        //Then
        Assertions.assertNotNull(cardFromDB.getCardId());
        Assertions.assertNull(cardFromDB.getUser());
        Assertions.assertEquals("accessPass1", cardFromDB.getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardFromDB.getCardStatus());

        //CleanUp
        cardRepository.delete(card);
    }

    @Test
    void createCardTest() {
        //Given
        CardCreateDto cardCreateDto = new CardCreateDto(null, "accessPass1", CardStatus.AVAILABLE);

        //When
        Card card = cardService.createCard(cardCreateDto);

        //Then
        Assertions.assertNotNull(card.getCardId());

        //CleanUp
        cardRepository.delete(card);
    }

    @Test
    void editCardTest() {
        //Given
        CardCreateDto cardCreateDto = new CardCreateDto(null, "accessPass1", CardStatus.AVAILABLE);
        Card card = cardService.createCard(cardCreateDto);
        CardEditDto cardEditDto = new CardEditDto(card.getCardId(), null, "accessPass2", CardStatus.LOST);

        //When
        Card editedCard = cardService.editCard(cardEditDto);

        //Then
        Assertions.assertNotNull(editedCard.getCardId());
        Assertions.assertNull(editedCard.getUser());
        Assertions.assertEquals("accessPass2", editedCard.getAccessPass());
        Assertions.assertEquals(CardStatus.LOST, editedCard.getCardStatus());
    }

    @Test
    void deleteCardTest() {
        //Given
        CardCreateDto cardCreateDto = new CardCreateDto(null, "accessPass1", CardStatus.AVAILABLE);
        Card card = cardService.createCard(cardCreateDto);

        //When
        cardRepository.delete(card);

        //Then
        boolean cardNotFound = false;
        Card cardAfterDelete = new Card();
        try {
            cardAfterDelete = cardService.getCardById(card.getCardId());
        } catch (CardNotFoundException e) {
            cardNotFound = true;
        }
        Assertions.assertNull(cardAfterDelete.getCardId());
        Assertions.assertTrue(cardNotFound);
    }
}
