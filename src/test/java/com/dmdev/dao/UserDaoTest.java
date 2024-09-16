package com.dmdev.dao;

import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.integration.IntegrationTestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest extends IntegrationTestBase {

    private static UserDao userDao;
    private static User testUser1;
    private static User testUser2;
    private static User testUser3;
    private static User testUser4;
    private static User testUser5;

    @BeforeAll
    static void setUp() {
        userDao = UserDao.getInstance();
        testUser1 = User.builder()
                .name("Mark")
                .email("mark@mail.ru")
                .birthday(LocalDate.of(1998, 3, 18))
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .password("12345")
                .build();
        testUser2 = User.builder()
                .name("Sonay")
                .email("sonay@mail.ru")
                .birthday(LocalDate.of(2000, 5, 28))
                .role(Role.USER)
                .gender(Gender.FEMALE)
                .password("abcdef")
                .build();
        testUser3 = User.builder()
                .name("Ivan")
                .email("ivan@gmail.com")
                .birthday(LocalDate.of(1990, 1, 10))
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .password("111")
                .build();
        testUser4 = User.builder()
                .name("Petr")
                .email("petr@gmail.com")
                .birthday(LocalDate.of(1995, 10, 19))
                .role(Role.USER)
                .gender(Gender.MALE)
                .password("123")
                .build();
        testUser5 = User.builder()
                .name("Sveta")
                .email("sveta@gmail.com")
                .birthday(LocalDate.of(2001, 12, 23))
                .role(Role.USER)
                .gender(Gender.FEMALE)
                .password("321")
                .build();
    }

    @Test
    void shouldSaveUserSuccessfully() {
        User savedUser1 = userDao.save(testUser1);
        User savedUser2 = userDao.save(testUser2);
        assertAll(
                () -> assertEquals(testUser1, savedUser1),
                () -> assertEquals(testUser2, savedUser2)
        );
    }

    @Test
    void shouldDeleteUserSuccessfully() {
        boolean isDeletedUser1 = userDao.delete(1);
        boolean isDeletedUser2 = userDao.delete(2);
        assertAll(
                () -> assertTrue(isDeletedUser1),
                () -> assertTrue(isDeletedUser2)
        );
    }

    @Test
    void shouldFindAllUsers() {
        List<User> allUsers = userDao.findAll();
        assertAll(
                () -> assertFalse(allUsers.isEmpty()),
                () -> assertEquals(5, allUsers.size()),
                () -> assertEquals(testUser3, allUsers.stream().filter(user -> user.getName().equals("Ivan")).findFirst().get()),
                () -> assertEquals(testUser4, allUsers.stream().filter(user -> user.getName().equals("Petr")).findFirst().get()),
                () -> assertEquals(testUser5, allUsers.stream().filter(user -> user.getName().equals("Sveta")).findFirst().get())
        );
    }

    @Test
    void shouldFindUserById() {
        Optional<User> foundUser1 = userDao.findById(1);
        Optional<User> foundUser2 = userDao.findById(2);
        Optional<User> foundUser3 = userDao.findById(3);
        assertAll(
                () -> assertEquals(testUser3, foundUser1.get()),
                () -> assertEquals(testUser4, foundUser2.get()),
                () -> assertEquals(testUser5, foundUser3.get())
        );
    }

    @Test
    void shouldFindUserByEmailAndPassword() {
        Optional<User> foundUser1 = userDao.findByEmailAndPassword(testUser3.getEmail(), testUser3.getPassword());
        Optional<User> foundUser2 = userDao.findByEmailAndPassword(testUser4.getEmail(), testUser4.getPassword());
        Optional<User> foundUser3 = userDao.findByEmailAndPassword(testUser5.getEmail(), testUser5.getPassword());
        Optional<User> foundUser4 = userDao.findByEmailAndPassword(testUser2.getEmail(), testUser5.getPassword());
        Optional<User> foundUser5 = userDao.findByEmailAndPassword(testUser5.getEmail(), testUser3.getPassword());
        assertAll(
                () -> assertEquals(testUser3, foundUser1.get()),
                () -> assertEquals(testUser4, foundUser2.get()),
                () -> assertEquals(testUser5, foundUser3.get()),
                () -> assertFalse(foundUser4.isPresent()),
                () -> assertFalse(foundUser5.isPresent())
        );
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        Optional<User> userToUpdate = userDao.findById(2);
        userToUpdate.get().setName("Morris");
        userToUpdate.get().setEmail("morris@mail.ru");
        userDao.update(userToUpdate.get());
        Optional<User> updatedUser = userDao.findById(2);
        assertEquals(userToUpdate.get(), updatedUser.get());
    }
}