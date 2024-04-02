package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;
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
        user.setUserEmail("amit@gmail.com");

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
        user.setUserEmail("amit@gmail.com");
        user.setUserDescription("Software Engineer");
        user.setUserGender("Male");
        user.setUserName("Amit");
        user.setUserPassword("password");

        User updatedUser = new User();
        updatedUser.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(updatedUser));
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user.getId(), userServiceImpl.updateUser(user).getId());
    }

    @Test
    void testSetUserEmail() {
        User user = new User();
        user.setId(1L);
        user.setUserEmail("xyz@gmail.com");

        when(userRepository.save(user)).thenReturn(user);
        assertEquals("test123@gmail.com", userServiceImpl.setUserEmail(user, "test123@gmail.com").getUserEmail());
    }

    @Test
    void testGetUserByEmailForExisting() {
        User user = new User();
        user.setId(1L);
        user.setUserEmail("amit123@gmail.com");

        when(userRepository.findByUserEmail(anyString())).thenReturn(java.util.Optional.of(user));
        assertEquals(user.getId(), userServiceImpl.getUserByEmail("amit123@gmail.com").getId());
    }

    @Test
    void testGetUserByEmailForNonExisting() {
        when(userRepository.findByUserEmail(anyString())).thenReturn(java.util.Optional.empty());
        assertNull(userServiceImpl.getUserByEmail("testForNull"));
    }

    @Test
    void testGetUserByNameAndEmailForExisting() {
        User user = new User();
        user.setId(1L);
        user.setUserEmail("amit132@gmail.com");

        when(userRepository.findByUserNameAndUserEmail(anyString(), anyString())).thenReturn(java.util.Optional.of(user));
        assertEquals(user.getId(), userServiceImpl.getUserByNameAndEmail("Amit", "amit132@gmail.com").getId());
    }

    @Test
    void testGetUserByNameAndEmailForNonExisting() {
        when(userRepository.findByUserNameAndUserEmail(anyString(), anyString())).thenReturn(java.util.Optional.empty());
        assertNull(userServiceImpl.getUserByNameAndEmail("testForNull", "testForNull"));
    }
}
