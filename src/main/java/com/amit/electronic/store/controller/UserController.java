package com.amit.electronic.store.controller;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.model.GetUserRequest;
import com.amit.electronic.store.model.UpdateUserRequest;
import com.amit.electronic.store.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    UserController(@Autowired UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public String testHome() {
        return "Welcome to Electronic Store, fellas!";
    }

    // create user endpoint
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userServiceImpl.create(user);
    }

    // get user endpoint
    @GetMapping("/user")
    public User getUser(@RequestBody GetUserRequest request) {
        return userServiceImpl.getUserBasedOnTheUserRequest(request);
    }

    // get all users endpoint
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    // update user endpoint
    @PostMapping("/user/{id}")
    public User updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable Long id) {
        return userServiceImpl.updateUser(updateUserRequest, id);
    }

    // delete user endpoint
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
    }

}
