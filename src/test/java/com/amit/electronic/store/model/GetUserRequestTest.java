package com.amit.electronic.store.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetUserRequestTest {

    @Test
    void testGetId() {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setId(1L);

        assertEquals(1, getUserRequest.getId());
    }

    @Test
    void testGetEmail() {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setEmail("test@gmail.com");

        assertEquals("test@gmail.com", getUserRequest.getEmail());
    }

    @Test
    void testGetName() {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setName("test");

        assertEquals("test", getUserRequest.getName());
    }
}
