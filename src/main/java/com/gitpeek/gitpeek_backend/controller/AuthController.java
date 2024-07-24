package com.gitpeek.gitpeek_backend.controller;

import com.gitpeek.gitpeek_backend.payload.LoginDto;
import com.gitpeek.gitpeek_backend.payload.RegisterDto;
import com.gitpeek.gitpeek_backend.payload.ResponseDto;
import com.gitpeek.gitpeek_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.Data;

@Data
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            ResponseDto responseMessage = new ResponseDto("User signed in successfully!", HttpStatus.OK.value());
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            LOGGER.log(Level.SEVERE, "Authentication failed", e);
            ResponseDto responseMessage = new ResponseDto("Invalid username or password", HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during sign in", e);
            ResponseDto responseMessage = new ResponseDto("An error occurred during sign in", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody RegisterDto registerDto) {
        try {
            userService.registerUser(registerDto.getUsername(), registerDto.getPassword());
            ResponseDto responseDto = new ResponseDto("User registered successfully!", HttpStatus.CREATED.value());
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Registration failed", e);
            ResponseDto responseDto = new ResponseDto("Username already taken", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during registration", e);
            ResponseDto responseDto = new ResponseDto("An error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception e) {
        LOGGER.log(Level.SEVERE, "An error occurred", e);
        ResponseDto responseDto = new ResponseDto("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
