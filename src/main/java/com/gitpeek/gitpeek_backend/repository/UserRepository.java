package com.gitpeek.gitpeek_backend.repository;

import com.gitpeek.gitpeek_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by their username
    Optional<User> findByUsername(String username);

    // Find a user by their ID
    Optional<User> findById(Long id);

    //Find a user by their username or ID
    Optional<User> findByUsernameOrId(String username, Long id);
}
