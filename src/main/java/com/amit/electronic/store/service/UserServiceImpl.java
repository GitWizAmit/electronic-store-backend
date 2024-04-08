package com.amit.electronic.store.service;

import com.amit.electronic.store.entity.User;
import com.amit.electronic.store.exception.InvalidUserRequestException;
import com.amit.electronic.store.model.GetUserRequest;
import com.amit.electronic.store.model.UpdateUserRequest;
import com.amit.electronic.store.repository.UserRepository;
import com.google.common.base.Preconditions;
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
    private static final String USER_NOT_FOUND = "User not found";

    public User create(User user) {
        Preconditions.checkNotNull(user, "User cannot be null");
        Preconditions.checkNotNull(user.getName(), "User name cannot be null");
        Preconditions.checkNotNull(user.getEmail(), "User email cannot be null");
        Preconditions.checkNotNull(user.getPassword(), "User password cannot be null");
        Preconditions.checkNotNull(user.getGender(), "User gender cannot be null");
        Preconditions.checkNotNull(user.getDescription(), "User description cannot be null");

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Preconditions.checkNotNull(id, "User id cannot be null");

        User user = userRepository.findById(id).orElse(null);
        Preconditions.checkNotNull(user, USER_NOT_FOUND);

        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(UpdateUserRequest updateUserRequest, Long id) {
        User updatedUser = userRepository.findById(id).orElse(null);
        if (updatedUser != null) {
            updatedUser.setName(updateUserRequest.getName());
            updatedUser.setGender(updateUserRequest.getGender());
            updatedUser.setDescription(updateUserRequest.getDescription());
            userRepository.save(updatedUser);
        }
        return updatedUser;
    }

    public User getUserById(Long id) {
        Preconditions.checkNotNull(id, "User id cannot be null");

        User user = userRepository.findById(id).orElse(null);
        Preconditions.checkNotNull(user, USER_NOT_FOUND);

        return user;
    }

    public User getUserBasedOnTheUserRequest(GetUserRequest getUserRequest) {
        Preconditions.checkNotNull(getUserRequest, "GetUserRequest cannot be null");
        if (getUserRequest.getId() != null) {
            User user = userRepository.findById(getUserRequest.getId()).orElse(null);
            Preconditions.checkNotNull(user, USER_NOT_FOUND);
            return user;
        } else if (getUserRequest.getName() != null && getUserRequest.getEmail() != null) {
            User user = userRepository.findByNameAndEmail(getUserRequest.getName(), getUserRequest.getEmail());
            Preconditions.checkNotNull(user, USER_NOT_FOUND);
            return user;
        } else if (getUserRequest.getEmail() != null) {
            User user = userRepository.findByEmail(getUserRequest.getEmail());
            Preconditions.checkNotNull(user, USER_NOT_FOUND);
            return user;
        }

        throw  new InvalidUserRequestException("Invalid user request");
    }
}
