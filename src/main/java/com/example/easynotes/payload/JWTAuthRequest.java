package com.example.easynotes.payload;

import lombok.Data;

@Data
public class JWTAuthRequest {

    private String username;

    private String password;
}
