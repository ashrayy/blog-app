package com.example.easynotes.service;

import com.example.easynotes.model.Comment;
import com.example.easynotes.payload.CommentRequestDTO;
import com.example.easynotes.payload.CommentResponseDTO;

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
