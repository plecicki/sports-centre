package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.domain.*;
import com.kodilla.sportscentre.exceptions.CardNotFoundByUserId;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.exceptions.UserNotFoundException;
import com.kodilla.sportscentre.services.UserCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usercard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserCardContr {

    private final UserCardService userCardService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/clone")
    public ResponseEntity<UserOldNewDto> editUserWithClone(@RequestBody UserEditDto userEditDto) throws UserNotFoundException {
        return ResponseEntity.ok(userCardService.editUserWithClone(userEditDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editUser(@RequestBody UserEditDto userEditDto) throws CardNotFoundException, UserNotFoundException {
        return ResponseEntity.ok(userCardService.editUser(userEditDto));
    }

    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userCardService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) throws CardNotFoundException {
        return ResponseEntity.ok(userCardService.createUser(userCreateDto));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<Card> getCardByUserId(@PathVariable Long userId) throws CardNotFoundByUserId {
        return ResponseEntity.ok(userCardService.getCardByUserId(userId));
    }

    @DeleteMapping(value = "card/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) throws CardNotFoundException {
        userCardService.deleteCard(cardId);
        return ResponseEntity.ok().build();
    }
}
