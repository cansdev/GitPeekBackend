package com.gitpeek.gitpeek_backend.controller;

import com.gitpeek.gitpeek_backend.payload.LoginDto;
import com.gitpeek.gitpeek_backend.payload.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<ResponseMessage> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsernameOrId(),
                            loginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            ResponseMessage responseMessage = new ResponseMessage("User signed in successfully!", HttpStatus.OK.value());
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            ResponseMessage responseMessage = new ResponseMessage("Invalid username or password", HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage("An error occurred during sign in", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
