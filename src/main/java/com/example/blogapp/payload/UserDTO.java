package com.example.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {


    private int id;
    @NotNull
    @NotEmpty
    @Size(max=20,min=4,message = "Name must be between 4-10 characters")
    private String name;
    @Email
    @NotEmpty
    @NotNull
    @Size(max=20,min=4,message = "Email must be between 4-10 characters")
    private String email;
    @NotNull
    @NotEmpty
    @Size(max=20,min=4,message = "Password must be between 4-10 characters")
    private String password;
    @NotNull
    @NotEmpty
    @Size(max=20,min=4,message = "About must be between 4-10 characters")
    private String about;
}
