package com.amit.electronic.store.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("amit@gmail.com");
        user.setPassword("password");
        user.setName("Amit");
        user.setGender("Male");
        user.setDescription("Software Engineer");

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

        assertEquals("User(id=1, name=test, email=test@gmail.com, gender=Male, " +
                "password=password, description=Software Engineer)", user.toString());
    }

    @Test
    void testBuilder() {
        User user = User.builder()
                .id(1L)
                .name("test")
                .email("test@gmail.com")
                .password("password")
                .gender("male")
                .description("Software Engineer")
                .build();

        assertEquals(1L, user.getId());
    }
}
