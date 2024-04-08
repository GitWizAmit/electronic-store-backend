package com.amit.electronic.store.repository;

import com.amit.electronic.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByNameAndEmail(String userName, String email);

}
