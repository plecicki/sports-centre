package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.*;
import com.kodilla.sportscentre.domain.enums.CardStatus;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.exceptions.UserNotFoundException;
import com.kodilla.sportscentre.repositories.CardRepository;
import com.kodilla.sportscentre.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
public class UserCardServiceTest {

    @Autowired
    UserCardService userCardService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Test
    void editUserTest() throws Exception {
        //Given
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);
        User user = new User(0L,
                "nameOld", "surnameOld",
                LocalDate.of(1990, 12, 12),
                "emailOld",
                "phoneOld",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                new ArrayList<>(),
                LocalDate.now().plusMonths(1)
        );
        user = userRepository.save(user);
        UserEditDto userEditDto = new UserEditDto(user.getUserId(),
                "nameNew", "surnameNew",
                LocalDate.of(1990, 12, 12),
                "emailNew",
                "phoneNew",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                LocalDate.now().plusMonths(1)
        );

        //When
        UserOldNew userOldNew = userCardService.editUser(userEditDto);

        //Then
        Assertions.assertEquals("nameOld", userOldNew.getOldUser().getName());
        Assertions.assertEquals("surnameOld", userOldNew.getOldUser().getSurname());
        Assertions.assertEquals("emailOld", userOldNew.getOldUser().getEmail());
        Assertions.assertEquals("phoneOld", userOldNew.getOldUser().getPhone());
        Assertions.assertEquals("nameNew", userOldNew.getNewUser().getName());
        Assertions.assertEquals("surnameNew", userOldNew.getNewUser().getSurname());
        Assertions.assertEquals("emailNew", userOldNew.getNewUser().getEmail());
        Assertions.assertEquals("phoneNew", userOldNew.getNewUser().getPhone());

        //CleanUp
        userRepository.delete(userOldNew.getNewUser());
        cardRepository.delete(savedCard);
    }

    @Test
    void deleteUser() throws Exception {
        //Given
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);
        User user = new User(0L,
                "nameOld", "surnameOld",
                LocalDate.of(1990, 12, 12),
                "emailOld",
                "phoneOld",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                new ArrayList<>(),
                LocalDate.now().plusMonths(1)
        );
        user = userRepository.save(user);
        Card cardToEdit = new Card(savedCard.getCardId(), user, "accessPass1", CardStatus.AVAILABLE);
        Card editedCard = cardRepository.save(cardToEdit);

        //When
        userCardService.deleteUser(user.getUserId());

        //Then
        boolean userNotFound = false;
        User userAfterDelete = new User();
        try {
            userAfterDelete = userRepository.findById(user.getUserId()).orElseThrow(Exception::new);
        } catch (Exception e) {
            userNotFound = true;
        }
        Assertions.assertNull(userAfterDelete.getUserId());
        Assertions.assertTrue(userNotFound);
        Assertions.assertNull(cardRepository.findByCardId(editedCard.getCardId()).orElseThrow(CardNotFoundException::new).getUser());

        //CleanUp
        cardRepository.delete(cardRepository.findByCardId(editedCard.getCardId()).orElseThrow(CardNotFoundException::new));
    }

    @Test
    void createUserTest() throws CardNotFoundException {
        //Given
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);

        UserCreateDto userCreateDto = new UserCreateDto(
                "name", "surname",
                LocalDate.of(1990, 12, 12),
                "email",
                "phone",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                LocalDate.now().plusMonths(1)
        );

        //When
        User user = userCardService.createUser(userCreateDto);

        //Then
        Assertions.assertEquals(user.getUserId(), cardRepository.findByCardId(savedCard.getCardId()).
                orElseThrow(CardNotFoundException::new).getUser().getUserId());
        Assertions.assertNotNull(user.getUserId());

        //CleanUp
        User userToDelete = new User(user.getUserId(),
                "name", "surname",
                LocalDate.of(1990, 12, 12),
                "email",
                "phone",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.now().plusMonths(1)
        );
        Card cardToDelete = new Card(savedCard.getCardId(), null, "accessPass1", CardStatus.AVAILABLE);
        userRepository.save(userToDelete);
        cardRepository.save(cardToDelete);
        userRepository.delete(userToDelete);
        cardRepository.delete(cardToDelete);
    }
}
