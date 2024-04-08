package com.amit.electronic.store.controller;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.model.GetUserRequest;
import com.amit.electronic.store.model.UpdateUserRequest;
import com.amit.electronic.store.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

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
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.userEmail").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testGetUserById() throws Exception {
        GetUserRequest request = new GetUserRequest();
        request.setId(1L);

        String json = objectMapper.writeValueAsString(request);

        User user = new User();
        user.setId(1L);
        when(userService.getUserBasedOnTheUserRequest(any(GetUserRequest.class))).thenReturn(user);
        mockMvc.perform(get("/store/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists()); // Check if the user id is present in the response

    }

    @Test
    void testGetUserByEmail() throws Exception {
        GetUserRequest request = new GetUserRequest();
        request.setEmail("test@gmail.com");

        String json = objectMapper.writeValueAsString(request);

        when(userService.getUserBasedOnTheUserRequest(request)).thenReturn(new User());
        mockMvc.perform(get("/store/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User(); // Create a user object for testing
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/store/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").doesNotExist()); // Check if the user email is not present in the response
    }

    @Test
    void testUpdateUser() throws Exception {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setName("test");
        request.setDescription("test description");
        request.setGender("male");

        Long id = 1L;
        User user = new User();
        user.setEmail("test@gmail.com");
        when(userService.updateUser(any(UpdateUserRequest.class), eq(id))).thenReturn(user);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/store/user/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").exists()); // Check if the user email is present in the response
    }

    @Test
    void testDeleteUser() throws Exception {
        Long id = 1L;
        doNothing().when(userService).deleteUser(id);

        mockMvc.perform(delete("/store/user/{id}", id))
                .andExpect(status().isOk());
    }

}
