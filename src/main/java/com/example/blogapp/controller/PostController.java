package com.example.blogapp.controller;

import com.example.blogapp.config.AppConstants;
import com.example.blogapp.payload.PostRequestDTO;
import com.example.blogapp.payload.PostResponseDTO;
import com.example.blogapp.service.FileService;
import com.example.blogapp.service.PostService;
import com.example.blogapp.service.impl.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FileService fileService;

    @Value("${project.image}")
    String path;

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
    public ResponseEntity<PageResponse> getAllPosts(@RequestParam(value = AppConstants.PAGE_NUMBER, defaultValue = "1", required = false)Integer pageNumber,
                                                    @RequestParam(value = AppConstants.PAGE_SIZE , defaultValue = "10", required = false)Integer pageSize,
                                                    @RequestParam(value =AppConstants.SORT_BY , defaultValue = "id", required = false)String sortBy,
                                                    @RequestParam(value = AppConstants.SORT_DIR, defaultValue = "asc", required = false) String sortDir){
        return new ResponseEntity<PageResponse>(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse> getAllPostsBySearchText(@RequestParam(value ="q" , defaultValue = "1", required = false)String searchText){
        return new ResponseEntity<PageResponse>(this.postService.searchPosts(searchText),HttpStatus.OK);
    }

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostResponseDTO> uploadImage(@RequestParam("image")MultipartFile image, @PathVariable Integer postId) throws IOException {
       PostResponseDTO postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        PostRequestDTO req = this.modelMapper.map(postDto,PostRequestDTO.class);
        req.setUser_id(postDto.getUser().getId());
        req.setCategory_id(postDto.getCategory().getCategoryId());
       PostResponseDTO updatedPost = this.postService.updatePost(req,postId);
       return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse resp) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName );
        resp.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,resp.getOutputStream());
    }


}
