package com.amit.electronic.store.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testUser() {
        User user = new User();
        user.setId(1L);
        user.setUserEmail("amit@gmail.com");
        user.setUserPassword("password");
        user.setUserName("Amit");
        user.setUserGender("Male");
        user.setUserDescription("Software Engineer");

        assertEquals(1L, user.getId());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User(1L, "test", "test@gmail.com",
                "Male", "password", "Software Engineer");

        assertEquals(1L, user.getId());
    }

    @Test
    void testToString() {
        User user = new User(1L, "test", "test@gmail.com",
                "Male", "password", "Software Engineer");

        assertEquals("User(id=1, userName=test, userEmail=test@gmail.com, userGender=Male, " +
                "userPassword=password, userDescription=Software Engineer)", user.toString());
    }

    @Test
    void testBuilder() {
        User user = User.builder()
                .id(1L)
                .userName("test")
                .userEmail("test@gmail.com")
                .userPassword("password")
                .userGender("male")
                .userDescription("Software Engineer")
                .build();

        assertEquals(1L, user.getId());
    }
}
