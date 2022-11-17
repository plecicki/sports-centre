package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserCreateDto;
import com.kodilla.sportscentre.domain.UserEditDto;
import com.kodilla.sportscentre.exceptions.UserNotFoundException;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(final Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User createUser(final UserCreateDto userCreateDto) {
        User user = userMapper.mapToUserFromCreate(userCreateDto);
        return userRepository.save(user);
    }

    public User editUser(final UserEditDto userEditDto) {
        User user = userMapper.mapToUserFromEdit(userEditDto);
        return userRepository.save(user);
    }

    public void deleteUser(final Long userId) throws UserNotFoundException {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException();
        userRepository.deleteById(userId);
    }
}
