package com.example.blogapp.service.impl;

import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.payload.CommentRequestDTO;
import com.example.blogapp.payload.CommentResponseDTO;
import com.example.blogapp.repository.CommentRepo;
import com.example.blogapp.repository.UserRepo;
import com.example.blogapp.service.CommentService;
import com.example.blogapp.model.Category;
import com.example.blogapp.model.Comment;
import com.example.blogapp.model.Post;
import com.example.blogapp.model.User;
import com.example.blogapp.repository.CategoryRepo;
import com.example.blogapp.repository.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentRepo commentRepo;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  UserRepo userRepo;

  @Autowired
  CategoryRepo categoryRepo;

  @Autowired
  PostRepo postRepo;


    @Override
    public CommentResponseDTO getCommentById(Integer id) {
        return this.modelMapper.map(this.commentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","Comment-id",id)),CommentResponseDTO.class);
    }

    @Override
    public List<Comment> getAllComments() {
        return null;
    }

    @Override
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
      User user = userRepo.findById(commentRequestDTO.getUser_id()).orElseThrow(()-> new ResourceNotFoundException("User","User-id",commentRequestDTO.getUser_id()));
      Category category = categoryRepo.findById(commentRequestDTO.getCategory_id()).orElseThrow(()-> new ResourceNotFoundException("Category","Category-id",commentRequestDTO.getCategory_id()));
      Post post = postRepo.findById(commentRequestDTO.getPost_id()).orElseThrow(()-> new ResourceNotFoundException("Post","Post-id",commentRequestDTO.getPost_id()));
      Comment comment = new Comment();
      comment.setCategory(category);
      comment.setUser(user);
      comment.setContent(commentRequestDTO.getContent());
      comment.setPost(post);
      this.commentRepo.save(comment);
      return this.modelMapper.map(comment,CommentResponseDTO.class);
    }

    @Override
    public CommentResponseDTO updateComment(CommentRequestDTO commentRequestDTO) {
        return null;
    }

    @Override
    public List<CommentResponseDTO> getCommentByUserId(Integer id) {
      User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "user-id" ,id));
      List<Comment> lst = this.commentRepo.findAll();
      List<Comment> result = new ArrayList<>();
      for (Comment comment : lst) {
        if(comment.getUser().getId() == id){
          result.add(comment);
        }
      }
      return result.stream().map(val->this.modelMapper.map(val,CommentResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CommentResponseDTO getCommentByCategoryId(Integer id) {
        return null;
    }

    @Override
    public void deleteCommentById(Integer id) {

    }
}

