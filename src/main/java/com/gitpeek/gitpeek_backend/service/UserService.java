package com.gitpeek.gitpeek_backend.service;

import com.gitpeek.gitpeek_backend.entity.User;
import com.gitpeek.gitpeek_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void registerUser(String username, String password) {
        LOGGER.log(Level.INFO, "Attempting to register user with username: {0}", username);

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be null or empty");
        }

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            LOGGER.log(Level.WARNING, "Registration failed: Username already taken: {0}", username);
            throw new IllegalArgumentException("Username already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        LOGGER.log(Level.INFO, "User successfully registered: {0}", username);
    }
}
