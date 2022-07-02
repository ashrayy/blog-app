package com.example.easynotes.controller;

import com.example.easynotes.payload.CommentRequestDTO;
import com.example.easynotes.payload.CommentResponseDTO;
import com.example.easynotes.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Integer id){
        return new ResponseEntity<>(this.commentService.getCommentById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentRequestDTO commentRequestDTO){
        return new ResponseEntity<>(this.commentService.createComment(commentRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByUserId(@PathVariable Integer id ){
        return new ResponseEntity<>(this.commentService.getCommentByUserId(id),HttpStatus.OK);
    }
}
