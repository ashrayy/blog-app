package com.example.blogapp.repo;

import com.example.blogapp.model.User;
import com.example.blogapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepoTest {

    User user = new User();

    @Autowired
    UserRepo userRepo;



}
