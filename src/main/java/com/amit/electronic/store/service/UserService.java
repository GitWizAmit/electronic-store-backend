package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.model.UpdateUserRequest;

import java.util.List;

public interface UserService {

    // create user
    User create(User user);
    // get user by userId
    User getUserById(Long id);
    // get all users
    List<User> getAllUsers();
    // update user
    User updateUser(UpdateUserRequest updateUserRequest, Long id);
    // delete user
    void deleteUser(Long id);
}

