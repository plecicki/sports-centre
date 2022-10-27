package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.*;
import com.kodilla.sportscentre.exceptions.CardNotFoundByUserId;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.exceptions.UserNotFoundException;
import com.kodilla.sportscentre.mappers.CardMapper;
import com.kodilla.sportscentre.mappers.UserMapper;
import com.kodilla.sportscentre.repositories.CardRepository;
import com.kodilla.sportscentre.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserOldNewDto editUserWithClone(final UserEditDto userEditDto) throws UserNotFoundException {
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
        UserOldNewDto userOldNewDto = new UserOldNewDto(oldUser, newUser);
        return userOldNewDto;
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
        Card card = cardRepository.findByCardId(user.getCard().getCardId()).orElseThrow(CardNotFoundException::new);
        User savedUser;
        if (card.getUser() == null) {
            savedUser = userRepository.save(user);
            card.setUser(savedUser);
            cardRepository.save(card);
        } else {
            throw new CardNotFoundException();
        }
        return savedUser;
    }

    public User editUser(final UserEditDto userEditDto) throws CardNotFoundException, UserNotFoundException {
        User oldUser = userRepository.findById(userEditDto.getUserId()).orElseThrow(UserNotFoundException::new);
        Card oldCard = cardRepository.findByCardId(oldUser.getCard().getCardId()).orElseThrow(CardNotFoundException::new);
        oldCard.setUser(null);
        cardRepository.save(oldCard);

        User user = userMapper.mapToUserFromEdit(userEditDto);
        Card card = cardRepository.findByCardId(user.getCard().getCardId()).orElseThrow(CardNotFoundException::new);
        User savedUser;
        if (card.getUser() == null) {
            savedUser = userRepository.save(user);
            card.setUser(savedUser);
            cardRepository.save(card);
        } else {
            throw new CardNotFoundException();
        }
        return savedUser;
    }

    public Card getCardByUserId(final Long userId) throws CardNotFoundByUserId {
        Card card = cardRepository.findByUser_UserId(userId).orElseThrow(CardNotFoundByUserId::new);
        return card;
    }

    public void deleteCard(final Long cardId) throws CardNotFoundException {
        if(!cardRepository.existsById(cardId)) throw new CardNotFoundException();
        if (userRepository.findByCard_CardId(cardId).isPresent()) {
            User user = userRepository.findByCard_CardId(cardId).get();
            user.setCard(null);
            userRepository.save(user);
        }
        cardRepository.deleteById(cardId);
    }
}
