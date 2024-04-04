package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.model.GetUserRequest;
import com.amit.electronic.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User updatedUser = userRepository.findById(user.getId()).orElse(null);
        if (updatedUser != null) {
            updatedUser.setName(user.getName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setGender(user.getGender());
            updatedUser.setDescription(user.getDescription());
            userRepository.save(updatedUser);
        }
        return updatedUser;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserBasedOnTheUserRequest(GetUserRequest getUserRequest) {
        if (getUserRequest.getId() != null) {
            return userRepository.findById(getUserRequest.getId()).orElse(null);
        } else if (getUserRequest.getName() != null && getUserRequest.getEmail() != null) {
            Optional<User> user = userRepository.findByNameAndEmail(getUserRequest.getName(), getUserRequest.getEmail());
            return user.orElse(null);
        } else if (getUserRequest.getEmail() != null) {
            Optional<User> user = userRepository.findByEmail(getUserRequest.getEmail());
            return user.orElse(null);
        } else {
            // Handle invalid or incomplete request
            return null;
        }
    }
}
