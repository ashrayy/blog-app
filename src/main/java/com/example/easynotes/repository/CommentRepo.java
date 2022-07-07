package com.example.easynotes.repository;

import com.example.easynotes.model.Comment;
import com.example.easynotes.model.Post;
import com.example.easynotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByUser(User user);
}
