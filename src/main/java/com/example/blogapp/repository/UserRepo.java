package com.example.blogapp.repository;

import com.example.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

 Optional<User> findByEmail(String email);
}
