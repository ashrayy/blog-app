package com.example.easynotes.controller;

import com.example.easynotes.payload.PostRequestDTO;
import com.example.easynotes.payload.PostResponseDTO;
import com.example.easynotes.service.PostService;
import com.example.easynotes.service.impl.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postRequestDTO){
        return new ResponseEntity<PostResponseDTO>(this.postService.createPost(postRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostResponseDTO>> getPostByUser(@PathVariable Integer id){
        return new ResponseEntity<>(this.postService.getPostByUser(id),HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostResponseDTO>> getPostByCategory(@PathVariable Integer id){
        return new ResponseEntity<>(this.postService.getPostByCategory(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Integer id){
        return new ResponseEntity<>(this.postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updateById(@RequestBody PostRequestDTO postRequestDTO, @PathVariable Integer id){
        return new ResponseEntity<>(this.postService.updatePost(postRequestDTO,id),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<PageResponse> getAllPosts(@RequestParam(value ="pageNumber" , defaultValue = "1", required = false)Integer pageNumber,
                                                    @RequestParam(value ="pageSize" , defaultValue = "10", required = false)Integer pageSize,
                                                    @RequestParam(value ="sortBy" , defaultValue = "id", required = false)String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        return new ResponseEntity<PageResponse>(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse> getAllPostsBySearchText(@RequestParam(value ="q" , defaultValue = "1", required = false)String searchText){
        return new ResponseEntity<PageResponse>(this.postService.searchPosts(searchText),HttpStatus.OK);
    }


}
