package com.example.easynotes.repo;

import com.example.easynotes.model.User;
import com.example.easynotes.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepoTest {

    User user = new User();

    @Autowired
    UserRepo userRepo;



}
