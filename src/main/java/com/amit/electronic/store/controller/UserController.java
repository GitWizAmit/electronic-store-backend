package com.amit.electronic.store.controller;

import com.amit.electronic.store.entity.User;
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
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userServiceImpl.create(user);
    }

    // get single user by id endpoint
    @GetMapping("/get/user/id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }

    // get all users endpoint
    @GetMapping("/getAll/users")
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    // get single user by email endpoint
    @GetMapping("/get/user/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userServiceImpl.getUserByEmail(email);
    }

    // update user endpoint
    @PostMapping("/user/update")
    public User updateUser(@RequestBody User user) {
        return userServiceImpl.updateUser(user);
    }

    // set user email endpoint
    @PostMapping("/user/set/email/{email}")
    public User setUserEmail(@RequestBody User user, @PathVariable String email) {
        return userServiceImpl.setUserEmail(user, email);
    }

    // delete user endpoint
    @DeleteMapping("/delete/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(userServiceImpl.getUserById(id));
    }

    // get user by name and email endpoint
    @GetMapping("/get/user/{name}/{email}")
    public User getUserByNameAndEmail(@PathVariable String name, @PathVariable String email) {
        return userServiceImpl.getUserByNameAndEmail(name, email);
    }

}
