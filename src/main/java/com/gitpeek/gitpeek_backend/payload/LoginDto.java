package com.gitpeek.gitpeek_backend.payload;

import lombok.Data;

@Data
public class LoginDto {
        private String usernameOrId;
        private String password;
    }


