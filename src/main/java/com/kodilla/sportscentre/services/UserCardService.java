package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.*;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.exceptions.UserNotFoundException;
import com.kodilla.sportscentre.mappers.CardMapper;
import com.kodilla.sportscentre.mappers.UserMapper;
import com.kodilla.sportscentre.repositories.CardRepository;
import com.kodilla.sportscentre.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserOldNew editUser(final UserEditDto userEditDto) throws UserNotFoundException {
        User user = userRepository.findById(userEditDto.getUserId()).orElseThrow(UserNotFoundException::new);

        UserToClone userToClone = userMapper.mapFromUserToUTClone(user);

        UserToClone clonedOldUser = new UserToClone();
        try {
            clonedOldUser = userToClone.copy();
        } catch (CloneNotSupportedException exception) {
            System.out.println(exception);
        }

        User oldUser = userMapper.mapFromUTCloneToUser(clonedOldUser);
        User newUser = userRepository.save(userMapper.mapToUserFromEdit(userEditDto));
        UserOldNew userOldNew = new UserOldNew(oldUser, newUser);
        return userOldNew;
    }

    public void deleteUser(final Long userId) throws UserNotFoundException {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException();
        if (cardRepository.findByUser_UserId(userId).isPresent()) {
            Card card = cardRepository.findByUser_UserId(userId).get();
            card.setUser(null);
            cardRepository.save(card);
        }
        userRepository.deleteById(userId);
    }

    public User createUser(final UserCreateDto userCreateDto) throws CardNotFoundException {
        User user = userMapper.mapToUserFromCreate(userCreateDto);
        System.out.println(1);
        Card card = cardRepository.findByCardId(user.getCard().getCardId()).orElseThrow(CardNotFoundException::new);
        User savedUser = new User();
        if (card.getUser() == null) {
            savedUser = userRepository.save(user);
            card.setUser(user);
            cardRepository.save(card);
        } else {
            System.out.println(2);
            throw new CardNotFoundException();
        }
        return savedUser;
    }
}
