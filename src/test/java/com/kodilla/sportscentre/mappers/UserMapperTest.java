package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.UserCreateDto;
import com.kodilla.sportscentre.domain.UserEditDto;
import com.kodilla.sportscentre.domain.UserToClone;
import com.kodilla.sportscentre.domain.enums.Goals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void mapToUserFromCreateTest() {
        //Given
        UserCreateDto userCreateDto = new UserCreateDto(
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
                LocalDate.of(2022, 11, 18)
        );

        //When
        User user = userMapper.mapToUserFromCreate(userCreateDto);

        //Then
        Assertions.assertEquals("name1", user.getName());
        Assertions.assertEquals("surname1", user.getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), user.getBirthDate());
        Assertions.assertEquals("email1", user.getEmail());
        Assertions.assertEquals("phone1", user.getPhone());
        Assertions.assertEquals(Goals.HEALTH, user.getGoal());
        Assertions.assertEquals(true, user.getStudent());
        Assertions.assertEquals(true, user.getGym());
        Assertions.assertEquals(true, user.getSwimmingPool());
        Assertions.assertEquals(null, user.getCard());
        Assertions.assertEquals(true, user.getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), user.getSubValidity());
    }

    @Test
    void mapToUserFromEditTest() {
        //Given
        UserEditDto userEditDto = new UserEditDto(1L,
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
                LocalDate.of(2022, 11, 18)
        );

        //When
        User user = userMapper.mapToUserFromEdit(userEditDto);

        //Then
        Assertions.assertEquals("name1", user.getName());
        Assertions.assertEquals("surname1", user.getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), user.getBirthDate());
        Assertions.assertEquals("email1", user.getEmail());
        Assertions.assertEquals("phone1", user.getPhone());
        Assertions.assertEquals(Goals.HEALTH, user.getGoal());
        Assertions.assertEquals(true, user.getStudent());
        Assertions.assertEquals(true, user.getGym());
        Assertions.assertEquals(true, user.getSwimmingPool());
        Assertions.assertEquals(null, user.getCard());
        Assertions.assertEquals(true, user.getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), user.getSubValidity());
    }

    @Test
    void mapToUserCreateDtoTest() {
        //Given
        User user = new User(1L,
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

        //When
        UserCreateDto userCreateDto = userMapper.mapToUserCreateDto(user);

        //Then
        Assertions.assertEquals("name1", userCreateDto.getName());
        Assertions.assertEquals("surname1", userCreateDto.getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userCreateDto.getBirthDate());
        Assertions.assertEquals("email1", userCreateDto.getEmail());
        Assertions.assertEquals("phone1", userCreateDto.getPhone());
        Assertions.assertEquals(Goals.HEALTH, userCreateDto.getGoal());
        Assertions.assertEquals(true, userCreateDto.getStudent());
        Assertions.assertEquals(true, userCreateDto.getGym());
        Assertions.assertEquals(true, userCreateDto.getSwimmingPool());
        Assertions.assertEquals(null, userCreateDto.getCard());
        Assertions.assertEquals(true, userCreateDto.getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userCreateDto.getSubValidity());
    }

    @Test
    void mapToUserEditDtoTest() {
        //Given
        User user = new User(1L,
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

        //When
        UserEditDto userEditDto = userMapper.mapToUserEditDto(user);

        //Then
        Assertions.assertEquals("name1", userEditDto.getName());
        Assertions.assertEquals("surname1", userEditDto.getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userEditDto.getBirthDate());
        Assertions.assertEquals("email1", userEditDto.getEmail());
        Assertions.assertEquals("phone1", userEditDto.getPhone());
        Assertions.assertEquals(Goals.HEALTH, userEditDto.getGoal());
        Assertions.assertEquals(true, userEditDto.getStudent());
        Assertions.assertEquals(true, userEditDto.getGym());
        Assertions.assertEquals(true, userEditDto.getSwimmingPool());
        Assertions.assertEquals(null, userEditDto.getCard());
        Assertions.assertEquals(true, userEditDto.getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userEditDto.getSubValidity());
    }

    @Test
    void mapToUserCreateDtoListTest() {
        //Given
        User user1 = new User(1L,
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
        User user2 = new User(2L,
                "name2", "surname2",
                LocalDate.of(1990, 12, 12),
                "email2",
                "phone2",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        User user3 = new User(3L,
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
        List<User> userList = Arrays.asList(user1, user2, user3);

        //When
        List<UserCreateDto> userCreateDtoList = userMapper.mapToUserCreateDtoList(userList);

        //Then
        Assertions.assertEquals("name1", userCreateDtoList.get(0).getName());
        Assertions.assertEquals("surname1", userCreateDtoList.get(0).getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userCreateDtoList.get(0).getBirthDate());
        Assertions.assertEquals("email1", userCreateDtoList.get(0).getEmail());
        Assertions.assertEquals("phone1", userCreateDtoList.get(0).getPhone());
        Assertions.assertEquals(Goals.HEALTH, userCreateDtoList.get(0).getGoal());
        Assertions.assertEquals(true, userCreateDtoList.get(0).getStudent());
        Assertions.assertEquals(true, userCreateDtoList.get(0).getGym());
        Assertions.assertEquals(true, userCreateDtoList.get(0).getSwimmingPool());
        Assertions.assertEquals(null, userCreateDtoList.get(0).getCard());
        Assertions.assertEquals(true, userCreateDtoList.get(0).getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userCreateDtoList.get(0).getSubValidity());
        Assertions.assertEquals("name2", userCreateDtoList.get(1).getName());
        Assertions.assertEquals("surname2", userCreateDtoList.get(1).getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userCreateDtoList.get(1).getBirthDate());
        Assertions.assertEquals("email2", userCreateDtoList.get(1).getEmail());
        Assertions.assertEquals("phone2", userCreateDtoList.get(1).getPhone());
        Assertions.assertEquals(Goals.HEALTH, userCreateDtoList.get(1).getGoal());
        Assertions.assertEquals(true, userCreateDtoList.get(1).getStudent());
        Assertions.assertEquals(true, userCreateDtoList.get(1).getGym());
        Assertions.assertEquals(true, userCreateDtoList.get(1).getSwimmingPool());
        Assertions.assertEquals(null, userCreateDtoList.get(1).getCard());
        Assertions.assertEquals(true, userCreateDtoList.get(1).getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userCreateDtoList.get(1).getSubValidity());
        Assertions.assertEquals("name3", userCreateDtoList.get(2).getName());
        Assertions.assertEquals("surname3", userCreateDtoList.get(2).getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userCreateDtoList.get(2).getBirthDate());
        Assertions.assertEquals("email3", userCreateDtoList.get(2).getEmail());
        Assertions.assertEquals("phone3", userCreateDtoList.get(2).getPhone());
        Assertions.assertEquals(Goals.HEALTH, userCreateDtoList.get(2).getGoal());
        Assertions.assertEquals(true, userCreateDtoList.get(2).getStudent());
        Assertions.assertEquals(true, userCreateDtoList.get(2).getGym());
        Assertions.assertEquals(true, userCreateDtoList.get(2).getSwimmingPool());
        Assertions.assertEquals(null, userCreateDtoList.get(2).getCard());
        Assertions.assertEquals(true, userCreateDtoList.get(2).getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userCreateDtoList.get(2).getSubValidity());
    }

    @Test
    void mapToUserEditDtoListTest() {
        //Given
        User user1 = new User(1L,
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
        User user2 = new User(2L,
                "name2", "surname2",
                LocalDate.of(1990, 12, 12),
                "email2",
                "phone2",
                Goals.HEALTH,
                true,
                true,
                true,
                null,
                true,
                new ArrayList<>(),
                LocalDate.of(2022, 11, 18)
        );
        User user3 = new User(3L,
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
        List<User> userList = Arrays.asList(user1, user2, user3);

        //When
        List<UserEditDto> userEditDtoList = userMapper.mapToUserEditDtoList(userList);

        //Then
        Assertions.assertEquals("name1", userEditDtoList.get(0).getName());
        Assertions.assertEquals("surname1", userEditDtoList.get(0).getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userEditDtoList.get(0).getBirthDate());
        Assertions.assertEquals("email1", userEditDtoList.get(0).getEmail());
        Assertions.assertEquals("phone1", userEditDtoList.get(0).getPhone());
        Assertions.assertEquals(Goals.HEALTH, userEditDtoList.get(0).getGoal());
        Assertions.assertEquals(true, userEditDtoList.get(0).getStudent());
        Assertions.assertEquals(true, userEditDtoList.get(0).getGym());
        Assertions.assertEquals(true, userEditDtoList.get(0).getSwimmingPool());
        Assertions.assertEquals(null, userEditDtoList.get(0).getCard());
        Assertions.assertEquals(true, userEditDtoList.get(0).getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userEditDtoList.get(0).getSubValidity());
        Assertions.assertEquals("name2", userEditDtoList.get(1).getName());
        Assertions.assertEquals("surname2", userEditDtoList.get(1).getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userEditDtoList.get(1).getBirthDate());
        Assertions.assertEquals("email2", userEditDtoList.get(1).getEmail());
        Assertions.assertEquals("phone2", userEditDtoList.get(1).getPhone());
        Assertions.assertEquals(Goals.HEALTH, userEditDtoList.get(1).getGoal());
        Assertions.assertEquals(true, userEditDtoList.get(1).getStudent());
        Assertions.assertEquals(true, userEditDtoList.get(1).getGym());
        Assertions.assertEquals(true, userEditDtoList.get(1).getSwimmingPool());
        Assertions.assertEquals(null, userEditDtoList.get(1).getCard());
        Assertions.assertEquals(true, userEditDtoList.get(1).getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userEditDtoList.get(1).getSubValidity());
        Assertions.assertEquals("name3", userEditDtoList.get(2).getName());
        Assertions.assertEquals("surname3", userEditDtoList.get(2).getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userEditDtoList.get(2).getBirthDate());
        Assertions.assertEquals("email3", userEditDtoList.get(2).getEmail());
        Assertions.assertEquals("phone3", userEditDtoList.get(2).getPhone());
        Assertions.assertEquals(Goals.HEALTH, userEditDtoList.get(2).getGoal());
        Assertions.assertEquals(true, userEditDtoList.get(2).getStudent());
        Assertions.assertEquals(true, userEditDtoList.get(2).getGym());
        Assertions.assertEquals(true, userEditDtoList.get(2).getSwimmingPool());
        Assertions.assertEquals(null, userEditDtoList.get(2).getCard());
        Assertions.assertEquals(true, userEditDtoList.get(2).getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userEditDtoList.get(2).getSubValidity());
    }

    @Test
    void mapFromUTCloneToUserTest() {
        //Given
        UserToClone userToClone = new UserToClone(1L,
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
                LocalDate.of(2022, 11, 18)
        );

        //When
        User user = userMapper.mapFromUTCloneToUser(userToClone);

        //Then
        Assertions.assertEquals("name1", user.getName());
        Assertions.assertEquals("surname1", user.getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), user.getBirthDate());
        Assertions.assertEquals("email1", user.getEmail());
        Assertions.assertEquals("phone1", user.getPhone());
        Assertions.assertEquals(Goals.HEALTH, user.getGoal());
        Assertions.assertEquals(true, user.getStudent());
        Assertions.assertEquals(true, user.getGym());
        Assertions.assertEquals(true, user.getSwimmingPool());
        Assertions.assertEquals(null, user.getCard());
        Assertions.assertEquals(true, user.getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), user.getSubValidity());
    }

    @Test
    void mapFromUserToUTCloneTest() {
        //Given
        User user = new User(1L,
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

        //When
        UserToClone userToClone = userMapper.mapFromUserToUTClone(user);

        //Then
        Assertions.assertEquals("name1", userToClone.getName());
        Assertions.assertEquals("surname1", userToClone.getSurname());
        Assertions.assertEquals(LocalDate.of(1990, 12, 12), userToClone.getBirthDate());
        Assertions.assertEquals("email1", userToClone.getEmail());
        Assertions.assertEquals("phone1", userToClone.getPhone());
        Assertions.assertEquals(Goals.HEALTH, userToClone.getGoal());
        Assertions.assertEquals(true, userToClone.getStudent());
        Assertions.assertEquals(true, userToClone.getGym());
        Assertions.assertEquals(true, userToClone.getSwimmingPool());
        Assertions.assertEquals(null, userToClone.getCard());
        Assertions.assertEquals(true, userToClone.getAutoExtension());
        Assertions.assertEquals(LocalDate.of(2022, 11, 18), userToClone.getSubValidity());
    }
}
