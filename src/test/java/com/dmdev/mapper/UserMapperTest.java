package com.dmdev.mapper;

import com.dmdev.dto.UserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private static UserMapper userMapper;
    private static User user1;
    private static User user2;
    private static UserDto user3;
    private static UserDto user4;

    @BeforeAll
    static void setUp(){
        userMapper = UserMapper.getInstance();
        user1 = User.builder()
                .id(1)
                .name("Ivan")
                .email("ivan@gmail.com")
                .birthday(LocalDate.of(1990, 1, 10))
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .password("111")
                .build();
        user2 = User.builder()
                .id(2)
                .name("Petr")
                .email("petr@gmail.com")
                .birthday(LocalDate.of(1995, 10, 19))
                .role(Role.USER)
                .gender(Gender.MALE)
                .password("123")
                .build();
        user3 = UserDto.builder()
                .id(1)
                .name("Ivan")
                .email("ivan@gmail.com")
                .birthday(LocalDate.of(1990, 1, 10))
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .build();
        user4 = UserDto.builder()
                .id(2)
                .name("Petr")
                .email("petr@gmail.com")
                .birthday(LocalDate.of(1995, 10, 19))
                .role(Role.USER)
                .gender(Gender.MALE)
                .build();
    }

    @Test
    void mapTest() {
        UserDto dto1 = userMapper.map(user1);
        UserDto dto2 = userMapper.map(user2);

        assertAll(
                () -> assertEquals(dto1, user3),
                () -> assertEquals(dto2, user4)
        );
    }
}