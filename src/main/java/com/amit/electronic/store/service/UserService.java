package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;

import java.util.List;

public interface UserService {

    // create user
    User create(User user);
    // get user by userId
    User getUserById(Long id);
    // get user by userEmail
    User getUserByEmail(String email);
    // get all users
    List<User> getAllUsers();
    // set user email
    User setUserEmail(User user, String email);
    // update user
    User updateUser(User user);
    // delete user
    void deleteUser(User user);
    // get user by name and email
    User getUserByNameAndEmail(String name, String email);
}
