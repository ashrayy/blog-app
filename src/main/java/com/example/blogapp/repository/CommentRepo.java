package com.example.blogapp.repository;

import com.example.blogapp.model.Comment;
import com.example.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByUser(User user);
}
