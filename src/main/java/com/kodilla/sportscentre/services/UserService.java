package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.Card;
import com.kodilla.sportscentre.domain.CardDto;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserDto;
import com.kodilla.sportscentre.exceptions.CardNotFoundException;
import com.kodilla.sportscentre.exceptions.UserNotFoundException;
import com.kodilla.sportscentre.mappers.CardMapper;
import com.kodilla.sportscentre.mappers.UserMapper;
import com.kodilla.sportscentre.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.mapToUserDtoList(userList);
    }

    public UserDto getUserById(final Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }

    public UserDto saveUser(final User user) {
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    public void deleteUser(final Long userId) throws UserNotFoundException {
        userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(userId);
    }
}
