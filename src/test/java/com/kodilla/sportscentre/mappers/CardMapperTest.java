package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardCreateDto;
import com.kodilla.sportscentre.domain.CardEditDto;
import com.kodilla.sportscentre.domain.enums.CardStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class CardMapperTest {

    @Autowired
    private CardMapper cardMapper;

    @Test
    void mapToCardFromCreateTest() {
        //Given
        CardCreateDto cardCreateDto = new CardCreateDto(null, "accessPass1", CardStatus.AVAILABLE);

        //When
        Card card = cardMapper.mapToCardFromCreate(cardCreateDto);

        //Then
        Assertions.assertEquals(null, card.getUser());
        Assertions.assertEquals("accessPass1", card.getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, card.getCardStatus());
    }

    @Test
    void mapToCardFromEditTest() {
        //Given
        CardEditDto cardEditDto = new CardEditDto(1L, null, "accessPass1", CardStatus.AVAILABLE);

        //When
        Card card = cardMapper.mapToCardFromEdit(cardEditDto);

        //Then
        Assertions.assertEquals(null, card.getUser());
        Assertions.assertEquals("accessPass1", card.getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, card.getCardStatus());
    }

    @Test
    void mapToCardCreateDtoTest() {
        //Given
        Card card = new Card(1L, null, "accessPass1", CardStatus.AVAILABLE);

        //When
        CardCreateDto cardCreateDto = cardMapper.mapToCardCreateDto(card);

        //Then
        Assertions.assertEquals(null, cardCreateDto.getUser());
        Assertions.assertEquals("accessPass1", cardCreateDto.getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardCreateDto.getCardStatus());
    }

    @Test
    void mapToCardEditDtoTest() {
        //Given
        Card card = new Card(1L, null, "accessPass1", CardStatus.AVAILABLE);

        //When
        CardEditDto cardEditDto = cardMapper.mapToCardEditDto(card);

        //Then
        Assertions.assertEquals(null, cardEditDto.getUser());
        Assertions.assertEquals("accessPass1", cardEditDto.getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardEditDto.getCardStatus());
    }

    @Test
    void mapToCardCreateDtoListTest() {
        //Given
        Card card1 = new Card(1L, null, "accessPass1", CardStatus.AVAILABLE);
        Card card2 = new Card(2L, null, "accessPass2", CardStatus.AVAILABLE);
        Card card3 = new Card(3L, null, "accessPass3", CardStatus.AVAILABLE);
        List<Card> cardList = Arrays.asList(card1, card2, card3);

        //When
        List<CardCreateDto> cardCreateDtoList = cardMapper.mapToCardCreateDtoList(cardList);

        //Then
        Assertions.assertEquals(null, cardCreateDtoList.get(0).getUser());
        Assertions.assertEquals("accessPass1", cardCreateDtoList.get(0).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardCreateDtoList.get(0).getCardStatus());
        Assertions.assertEquals(null, cardCreateDtoList.get(1).getUser());
        Assertions.assertEquals("accessPass2", cardCreateDtoList.get(1).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardCreateDtoList.get(1).getCardStatus());
        Assertions.assertEquals(null, cardCreateDtoList.get(2).getUser());
        Assertions.assertEquals("accessPass3", cardCreateDtoList.get(2).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardCreateDtoList.get(2).getCardStatus());
    }

    @Test
    void mapToCardEditDtoListTest() {
        //Given
        Card card1 = new Card(1L, null, "accessPass1", CardStatus.AVAILABLE);
        Card card2 = new Card(2L, null, "accessPass2", CardStatus.AVAILABLE);
        Card card3 = new Card(3L, null, "accessPass3", CardStatus.AVAILABLE);
        List<Card> cardList = Arrays.asList(card1, card2, card3);

        //When
        List<CardEditDto> cardEditDtoList = cardMapper.mapToCardEditDtoList(cardList);

        //Then
        Assertions.assertEquals(null, cardEditDtoList.get(0).getUser());
        Assertions.assertEquals("accessPass1", cardEditDtoList.get(0).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardEditDtoList.get(0).getCardStatus());
        Assertions.assertEquals(null, cardEditDtoList.get(1).getUser());
        Assertions.assertEquals("accessPass2", cardEditDtoList.get(1).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardEditDtoList.get(1).getCardStatus());
        Assertions.assertEquals(null, cardEditDtoList.get(2).getUser());
        Assertions.assertEquals("accessPass3", cardEditDtoList.get(2).getAccessPass());
        Assertions.assertEquals(CardStatus.AVAILABLE, cardEditDtoList.get(2).getCardStatus());
    }
}
