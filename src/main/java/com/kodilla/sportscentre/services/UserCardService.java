package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserEditDto;
import com.kodilla.sportscentre.domain.UserOldNew;
import com.kodilla.sportscentre.domain.UserToClone;
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
}
