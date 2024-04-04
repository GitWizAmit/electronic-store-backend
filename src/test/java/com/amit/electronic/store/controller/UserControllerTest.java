package com.amit.electronic.store.controller;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.model.GetUserRequest;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void getUserById() {
        User user = new User();
        user.setId(1L);

        GetUserRequest request = new GetUserRequest();
        request.setId(1L);

        when(userService.getUserBasedOnTheUserRequest(request)).thenReturn(user);

        User foundUser = userController.getUser(request);

        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    void getUserByEmail() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");

        GetUserRequest request = new GetUserRequest();
        request.setEmail("test@gmail.com");

        when(userService.getUserBasedOnTheUserRequest(request)).thenReturn(user);

        User foundUser = userController.getUser(request);

        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    void getUserByNameAndEmail() {
        User user = new User();
        user.setId(1L);
        user.setEmail("123@gmail.com");
        user.setName("test");

        GetUserRequest request = new GetUserRequest();
        request.setEmail("123@gmail.com");
        request.setName("test");

        when(userService.getUserBasedOnTheUserRequest(request)).thenReturn(user);

        User foundUser = userController.getUser(request);

        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getName(), foundUser.getName());
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/store/get_users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userEmail").doesNotExist()); // Check if the user email is not present in the response
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

}
