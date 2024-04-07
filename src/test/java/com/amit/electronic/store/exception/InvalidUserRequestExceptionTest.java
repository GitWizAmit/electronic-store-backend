package com.amit.electronic.store.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidUserRequestExceptionTest {

    @Test
    void testInvalidUserRequestException() {
        InvalidUserRequestException invalidUserRequestException = new InvalidUserRequestException("Invalid User Request");

        assertEquals("Invalid User Request", invalidUserRequestException.getMessage());
    }
}
