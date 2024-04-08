package com.amit.electronic.store.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void testUpdateUserRequest() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setName("Amit");
        updateUserRequest.setDescription("Test Description");
        updateUserRequest.setGender("Male");

        assertEquals("Amit", updateUserRequest.getName());
        assertEquals("Test Description", updateUserRequest.getDescription());
        assertEquals("Male", updateUserRequest.getGender());
    }

}
