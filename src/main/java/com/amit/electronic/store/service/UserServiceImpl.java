package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User setUserEmail(User user, String email) {
        user.setUserEmail(email);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User updatedUser = userRepository.findById(user.getId()).orElse(null);
        if (updatedUser != null) {
            updatedUser.setUserName(user.getUserName());
            updatedUser.setUserEmail(user.getUserEmail());
            updatedUser.setUserPassword(user.getUserPassword());
            updatedUser.setUserGender(user.getUserGender());
            updatedUser.setUserDescription(user.getUserDescription());
            userRepository.save(updatedUser);
        }
        return updatedUser;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByUserEmail(email).orElse(null);
    }

    public User getUserByNameAndEmail(String name, String email) {
        return userRepository.findByUserNameAndUserEmail(name, email).orElse(null);
    }

}
