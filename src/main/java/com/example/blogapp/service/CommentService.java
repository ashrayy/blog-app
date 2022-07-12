package com.example.blogapp.service;

import com.example.blogapp.payload.CommentRequestDTO;
import com.example.blogapp.payload.CommentResponseDTO;
import com.example.blogapp.model.Comment;

import java.util.List;

public interface CommentService {

    CommentResponseDTO getCommentById(Integer id);

    List<Comment> getAllComments();

    CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO);

    CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> getCommentByUserId(Integer id);

    CommentResponseDTO getCommentByCategoryId(Integer id);

    void deleteCommentById(Integer id);






}
