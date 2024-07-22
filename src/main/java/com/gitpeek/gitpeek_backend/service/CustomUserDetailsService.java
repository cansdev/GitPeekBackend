//CustomUserDetailsService.java:
package com.gitpeek.gitpeek_backend.service;

import com.gitpeek.gitpeek_backend.entity.User;
import com.gitpeek.gitpeek_backend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrId) throws UsernameNotFoundException {
        User user;

        try {
            Long id = Long.parseLong(usernameOrId);
            user = userRepository.findById(id)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + usernameOrId));
        } catch (NumberFormatException e) {
            user = userRepository.findByUsername(usernameOrId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + usernameOrId));
        }

        Set<GrantedAuthority> authorities = Collections.emptySet();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
