package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserCreateDto;
import com.kodilla.sportscentre.domain.UserEditDto;
import com.kodilla.sportscentre.domain.UserOldNew;
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

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOldNew> editUser(@RequestBody UserEditDto userEditDto) throws UserNotFoundException {
        return ResponseEntity.ok(userCardService.editUser(userEditDto));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userCardService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto userCreateDto) throws CardNotFoundException {
        return ResponseEntity.ok(userCardService.createUser(userCreateDto));
    }
}
