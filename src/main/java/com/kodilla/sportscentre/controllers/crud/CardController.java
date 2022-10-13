package com.kodilla.sportscentre.controllers.crud;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardCreateDto;
import com.kodilla.sportscentre.domain.CardEditDto;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/card")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @GetMapping(value = "{cardId}")
    public ResponseEntity<Card> getCard(@PathVariable Long cardId) throws CardNotFoundException {
        return ResponseEntity.ok(cardService.getCardById(cardId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> createCard(@RequestBody CardCreateDto cardCreateDto) {
        return ResponseEntity.ok(cardService.createCard(cardCreateDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> editCard(@RequestBody CardEditDto cardEditDto) {
        return ResponseEntity.ok(cardService.editCard(cardEditDto));
    }

    @DeleteMapping(value = "{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) throws CardNotFoundException {
        cardService.deleteCard(cardId);
        return ResponseEntity.ok().build();
    }
}
