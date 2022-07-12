package com.example.blogapp.service;

import com.example.blogapp.service.impl.PageResponse;
import com.example.blogapp.payload.PostRequestDTO;
import com.example.blogapp.payload.PostResponseDTO;

import java.util.List;

public interface PostService {

    PostResponseDTO createPost(PostRequestDTO postRequestDTO);

    PostResponseDTO getPostById(Integer id);

    PostResponseDTO updatePost(PostRequestDTO postRequestDTO, Integer id);

    PageResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    void deletePost(Integer id);

    List<PostResponseDTO> getPostByUser(Integer id);

    List<PostResponseDTO> getPostByCategory(Integer id);

    PageResponse searchPosts(String searchText);
}
