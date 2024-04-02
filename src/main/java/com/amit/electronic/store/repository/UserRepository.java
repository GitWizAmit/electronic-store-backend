package com.amit.electronic.store.repository;

import com.amit.electronic.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(String email);

    Optional<User> findByUserNameAndUserEmail(String userName, String email);

}
