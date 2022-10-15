package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserCreateDto;
import com.kodilla.sportscentre.domain.UserEditDto;
import com.kodilla.sportscentre.domain.UserToClone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUserFromCreate(final UserCreateDto userCreateDto) {
        return new User(
                0L,
                userCreateDto.getName(),
                userCreateDto.getSurname(),
                userCreateDto.getBirthDate(),
                userCreateDto.getEmail(),
                userCreateDto.getPhone(),
                userCreateDto.getGoal(),
                userCreateDto.getStudent(),
                userCreateDto.getGym(),
                userCreateDto.getSwimmingPool(),
                userCreateDto.getCard(),
                userCreateDto.getAutoExtension(),
                new ArrayList<>()
        );
    }

    public User mapToUserFromEdit(final UserEditDto userEditDto) {
        return new User(
                userEditDto.getUserId(),
                userEditDto.getName(),
                userEditDto.getSurname(),
                userEditDto.getBirthDate(),
                userEditDto.getEmail(),
                userEditDto.getPhone(),
                userEditDto.getGoal(),
                userEditDto.getStudent(),
                userEditDto.getGym(),
                userEditDto.getSwimmingPool(),
                userEditDto.getCard(),
                userEditDto.getAutoExtension(),
                new ArrayList<>()
        );
    }

    public UserCreateDto mapToUserCreateDto(final User user) {
        return new UserCreateDto(
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getEmail(),
                user.getPhone(),
                user.getGoal(),
                user.getStudent(),
                user.getGym(),
                user.getSwimmingPool(),
                user.getCard(),
                user.getAutoExtension()
        );
    }

    public UserEditDto mapToUserEditDto(final User user) {
        return new UserEditDto(
                user.getUserId(),
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getEmail(),
                user.getPhone(),
                user.getGoal(),
                user.getStudent(),
                user.getGym(),
                user.getSwimmingPool(),
                user.getCard(),
                user.getAutoExtension()
        );
    }

    public List<UserCreateDto> mapToUserCreateDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserCreateDto)
                .collect(Collectors.toList());
    }

    public List<UserEditDto> mapToUserEditDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserEditDto)
                .collect(Collectors.toList());
    }

    public User mapFromUTCloneToUser(final UserToClone userToClone) {
        return new User(
                userToClone.getUserId(),
                userToClone.getName(),
                userToClone.getSurname(),
                userToClone.getBirthDate(),
                userToClone.getEmail(),
                userToClone.getPhone(),
                userToClone.getGoal(),
                userToClone.getStudent(),
                userToClone.getGym(),
                userToClone.getSwimmingPool(),
                userToClone.getCard(),
                userToClone.getAutoExtension(),
                new ArrayList<>()
        );
    }

    public UserToClone mapFromUserToUTClone(final User user) {
        return new UserToClone.UserToCloneBuilder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .phone(user.getPhone())
                .goal(user.getGoal())
                .student(user.getStudent())
                .gym(user.getGym())
                .swimmingPool(user.getSwimmingPool())
                .card(user.getCard())
                .autoExtension(user.getAutoExtension())
                .build();
    }
}
