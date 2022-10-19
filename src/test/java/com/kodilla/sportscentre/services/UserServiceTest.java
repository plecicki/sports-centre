package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserCreateDto;
import com.kodilla.sportscentre.domain.UserEditDto;
import com.kodilla.sportscentre.domain.enums.CardStatus;
import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.exceptions.UserNotFoundException;
import com.kodilla.sportscentre.repositories.CardRepository;
import com.kodilla.sportscentre.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Test
    void shouldFetchListWitchUsers() {
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);

        User user1 = new User(0L,
                "name1", "surname1",
                LocalDate.of(1990, 12, 12),
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        User user2 = new User(0L,
                "name2", "surname2",
                LocalDate.of(1990, 12, 12),
                "email2",
                "phone2",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        User user3 = new User(0L,
                "name3", "surname3",
                LocalDate.of(1990, 12, 12),
                "email3",
                "phone3",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //When
        List<User> users = userService.getAllUsers();

        //Then
        Assertions.assertEquals("name1", users.get(users.size()-3).getName());
        Assertions.assertEquals("surname1", users.get(users.size()-3).getSurname());
        Assertions.assertEquals("email1", users.get(users.size()-3).getEmail());
        Assertions.assertEquals("phone1", users.get(users.size()-3).getPhone());
        Assertions.assertEquals("name2", users.get(users.size()-2).getName());
        Assertions.assertEquals("surname2", users.get(users.size()-2).getSurname());
        Assertions.assertEquals("email2", users.get(users.size()-2).getEmail());
        Assertions.assertEquals("phone2", users.get(users.size()-2).getPhone());
        Assertions.assertEquals("name3", users.get(users.size()-1).getName());
        Assertions.assertEquals("surname3", users.get(users.size()-1).getSurname());
        Assertions.assertEquals("email3", users.get(users.size()-1).getEmail());
        Assertions.assertEquals("phone3", users.get(users.size()-1).getPhone());

        //CleanUp
        userRepository.delete(users.get(users.size() - 3));
        userRepository.delete(users.get(users.size() - 2));
        userRepository.delete(users.get(users.size() - 1));
        cardRepository.delete(savedCard);
    }

    @Test
    void shouldFetchUser() throws UserNotFoundException {
        //Given
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);

        User user = new User(0L,
                "name1", "surname1",
                LocalDate.of(1990, 12, 12),
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );

        User savedUser = userRepository.save(user);

        //When
        User userFromDB = userService.getUserById(savedUser.getUserId());

        //Then
        Assertions.assertEquals("name1", userFromDB.getName());
        Assertions.assertEquals("surname1", userFromDB.getSurname());
        Assertions.assertEquals("email1", userFromDB.getEmail());
        Assertions.assertEquals("phone1", userFromDB.getPhone());

        //CleanUp
        userRepository.delete(userFromDB);
        cardRepository.delete(savedCard);
    }

    @Test
    void createUserTest() {
        //Given
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);

        UserCreateDto userCreateDto = new UserCreateDto(
                "name1", "surname1",
                LocalDate.of(1990, 12, 12),
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                LocalDate.of(2022, 11, 18)
        );

        //When
        User user = userService.createUser(userCreateDto);

        //Then
        Assertions.assertNotNull(user.getUserId());

        //CleanUp
        userRepository.delete(user);
        cardRepository.delete(savedCard);
    }

    @Test
    void editUserTest() {
        //Given
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);

        User user = new User(0L,
                "name1", "surname1",
                LocalDate.of(1990, 12, 12),
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        User savedUser = userRepository.save(user);
        UserEditDto userEditDto = new UserEditDto(savedUser.getUserId(),
                "name2", "surname2",
                LocalDate.of(1990, 12, 12),
                "email2",
                "phone2",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                LocalDate.of(2022, 11, 18)
        );

        //When
        User editedUser = userService.editUser(userEditDto);

        //Then
        Assertions.assertEquals("name2", editedUser.getName());
        Assertions.assertEquals("surname2", editedUser.getSurname());
        Assertions.assertEquals("email2", editedUser.getEmail());
        Assertions.assertEquals("phone2", editedUser.getPhone());

        //CleanUp
        userRepository.delete(editedUser);
        cardRepository.delete(savedCard);
    }

    @Test
    void deleteUserTest() {
        //Given
        Card card = new Card(0L, null, "accessPass1", CardStatus.AVAILABLE);
        Card savedCard = cardRepository.save(card);

        User user = new User(0L,
                "name1", "surname1",
                LocalDate.of(1990, 12, 12),
                "email1",
                "phone1",
                Goals.HEALTH,
                true,
                true,
                true,
                savedCard,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        User savedUser = userRepository.save(user);

        //When
        try {
            userService.deleteUser(savedUser.getUserId());
        } catch (UserNotFoundException e) {
            System.out.println(e);
        }

        //Then
        boolean userNotFound = false;
        User userAfterDelete = new User();
        try {
            userAfterDelete = userService.getUserById(savedUser.getUserId());
        } catch (UserNotFoundException e) {
            userNotFound = true;
        }
        Assertions.assertNull(userAfterDelete.getUserId());
        Assertions.assertTrue(userNotFound);

        //CleanUp
        cardRepository.delete(savedCard);
    }
}
