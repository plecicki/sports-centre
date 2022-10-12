package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                0L,
                userDto.getName(),
                userDto.getSurname(),
                userDto.getBirthDate(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getGoal(),
                userDto.isStudent(),
                userDto.isGym(),
                userDto.isSwimmingPool(),
                userDto.getCard()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getEmail(),
                user.getPhone(),
                user.getGoal(),
                user.isStudent(),
                user.isGym(),
                user.isSwimmingPool(),
                user.getCard()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
