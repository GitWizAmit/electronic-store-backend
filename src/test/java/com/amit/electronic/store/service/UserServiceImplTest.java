package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.model.GetUserRequest;
import com.amit.electronic.store.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

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
    void testCreate() {
        User user = new User();
        user.setId(1L);
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userServiceImpl.create(user);
        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("amit@gmail.com");

        doNothing().when(userRepository).delete(user);
        userServiceImpl.deleteUser(user);
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
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(null);
        assertNull(userServiceImpl.updateUser(user));
    }

    @Test
    void testUpdateUserForExistingUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("amit@gmail.com");
        user.setDescription("Software Engineer");
        user.setGender("Male");
        user.setName("Amit");
        user.setPassword("password");

        User updatedUser = new User();
        updatedUser.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(updatedUser));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUserResponse = userServiceImpl.updateUser(user);

        assertEquals(user.getId(), updatedUserResponse.getId());
        assertEquals(user.getEmail(), updatedUserResponse.getEmail());
        assertEquals(user.getDescription(), updatedUserResponse.getDescription());
        assertEquals(user.getGender(), updatedUserResponse.getGender());
        assertEquals(user.getName(), updatedUserResponse.getName());
        assertEquals(user.getPassword(), updatedUserResponse.getPassword());
    }

    @Test
    void testSetUserEmail() {
        User user = new User();
        user.setId(1L);
        user.setEmail("xyz@gmail.com");

        when(userRepository.save(user)).thenReturn(user);
        assertEquals("test123@gmail.com", userServiceImpl.setUserEmail(user, "test123@gmail.com").getEmail());
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

        when(userRepository.findByEmail(any())).thenReturn(java.util.Optional.of(user));

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

        when(userRepository.findByNameAndEmail(anyString(), anyString())).thenReturn(java.util.Optional.of(user));

        assertEquals(user.getId(), userServiceImpl.getUserBasedOnTheUserRequest(getUserRequest).getId());
    }

    @Test
    void testGetUserBasedOnTheRequestWithNull() {
        User user = userServiceImpl.getUserBasedOnTheUserRequest(new GetUserRequest());
        assertNull(user);
    }
}
