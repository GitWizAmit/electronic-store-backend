package com.amit.electronic.store.controller;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/store"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Electronic Store, fellas!"));
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.create(user)).thenReturn(user);

        mockMvc.perform(post("/store/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/store/get/user/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/store/getAll/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testGetUserByEmail() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.getUserByEmail("test@example.com")).thenReturn(user);

        mockMvc.perform(get("/store/get/user/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.updateUser(user)).thenReturn(user);

        mockMvc.perform(post("/store/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testSetUserEmail() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.setUserEmail(user, "test@example.com")).thenReturn(user);

        mockMvc.perform(post("/store/user/set/email/test@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(any(User.class));

        mockMvc.perform(delete("/store/delete/user/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserByNameAndEmail() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.getUserByNameAndEmail("John", "test@example.com")).thenReturn(user);

        mockMvc.perform(get("/store/get/user/John/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

}
