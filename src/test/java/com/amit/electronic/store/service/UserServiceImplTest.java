package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.exception.InvalidUserRequestException;
import com.amit.electronic.store.model.GetUserRequest;
import com.amit.electronic.store.model.UpdateUserRequest;
import com.amit.electronic.store.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateHappyPath() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setName("test");
        user.setPassword("test");
        user.setDescription("Test Description");
        user.setGender("male");

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userServiceImpl.create(user);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userServiceImpl.deleteUser(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User foundUser = userServiceImpl.getUserById(1L);
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        assertEquals(0, userServiceImpl.getAllUsers().size());
    }

    @Test
    void testUpdateUserForNonExistingUser() {
        UpdateUserRequest user = new UpdateUserRequest();
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertNull(userServiceImpl.updateUser(user, 1L));
    }

    @Test
    void testUpdateUserForExistingUser() {
        UpdateUserRequest user = new UpdateUserRequest();
        user.setDescription("Software Engineer");
        user.setGender("Male");
        user.setName("Amit");

        User updatedUser = new User();
        updatedUser.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(updatedUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User updatedUserResponse = userServiceImpl.updateUser(user, 1L);

        assertEquals(user.getDescription(), updatedUserResponse.getDescription());
        assertEquals(user.getGender(), updatedUserResponse.getGender());
        assertEquals(user.getName(), updatedUserResponse.getName());
    }

    @Test
    void testGetUserBasedOnTheRequestWithId() {
        User user = new User();
        user.setId(1L);

        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        assertEquals(user.getId(), userServiceImpl.getUserBasedOnTheUserRequest(getUserRequest).getId());
    }

    @Test
    void testGetUserBasedOnTheRequestWithEmail() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");

        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setEmail("test@gmail.com");

        when(userRepository.findByEmail(any())).thenReturn(user);

        assertEquals(user.getId(), userServiceImpl.getUserBasedOnTheUserRequest(getUserRequest).getId());
    }

    @Test
    void testGetUserBasedOnTheRequestWithNameAndEmail() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setName("Amit");

        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setEmail("test@gmail.com");
        getUserRequest.setName("Amit");

        when(userRepository.findByNameAndEmail(anyString(), anyString())).thenReturn(user);

        assertEquals(user.getId(), userServiceImpl.getUserBasedOnTheUserRequest(getUserRequest).getId());
    }

    @Test
    void testGetUserBasedOnTheRequestWithNull() {
        try {
            userServiceImpl.getUserBasedOnTheUserRequest(new GetUserRequest());
        } catch (InvalidUserRequestException e) {
            assertEquals("Invalid user request", e.getMessage());
        }
    }
}
