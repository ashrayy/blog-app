package com.example.easynotes.service.impl;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Category;
import com.example.easynotes.model.Post;
import com.example.easynotes.model.User;
import com.example.easynotes.payload.PostRequestDTO;
import com.example.easynotes.payload.PostResponseDTO;
import com.example.easynotes.repository.CategoryRepo;
import com.example.easynotes.repository.PostRepo;
import com.example.easynotes.repository.UserRepo;
import com.example.easynotes.service.PostService;
import com.example.easynotes.util.SortUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        User user = this.userRepo.findById(postRequestDTO.getUser_id()).orElseThrow(()-> new ResourceNotFoundException("User","user-id", postRequestDTO.getUser_id()));
        Category category = this.categoryRepo.findById(postRequestDTO.getCategory_id()).orElseThrow(()-> new ResourceNotFoundException("Category","category-id", postRequestDTO.getCategory_id()));
        Post post = this.modelMapper.map(postRequestDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO getPostById(Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Post-id",id));
        return this.modelMapper.map(post,PostResponseDTO.class);
    }

    @Override
    public PostResponseDTO updatePost(PostRequestDTO postRequestDTO, Integer id) {
        Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Post-id",id));
        User user = this.userRepo.findById(postRequestDTO.getUser_id()).orElseThrow(()-> new ResourceNotFoundException("Post","Post-id",id));;
        Category category = this.categoryRepo.findById(postRequestDTO.getCategory_id()).orElseThrow(()-> new ResourceNotFoundException("Post","Post-id",id));
        post.setImageName(postRequestDTO.getImageName());
        post.setUser(user);
        post.setCategory(category);
        post.setTitle(postRequestDTO.getTitle());
        post.setContent(postRequestDTO.getContent());
        this.postRepo.save(post);
        return this.modelMapper.map(post,PostResponseDTO.class);
    }

    @Override
    public PageResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize,new SortUtils().getSortObj(sortBy,sortDir));
        Page pagePost = this.postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        PageResponse pg = new PageResponse();
        pg.setContent(posts.stream().map(val->this.modelMapper.map(val,PostResponseDTO.class)).collect(Collectors.toList()));
        pg.setPageSize(pageSize);
        pg.setPageNumber(pageNumber);
        pg.setTotalPages(pagePost.getTotalPages());
        pg.setLastPage(pagePost.isLast());
        pg.setTotalElements(pagePost.getNumberOfElements());
        return pg;
    }

    @Override
    public void deletePost(Integer id) {
      Post post = this.postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","Post-id",id));
      this.postRepo.deleteById(id);
    }

    @Override
    public List<PostResponseDTO> getPostByUser(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","User-id",id));
        List<Post> post = this.postRepo.findByUser(user);
        return post.stream().map(val->this.modelMapper.map(val,PostResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDTO> getPostByCategory(Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "Category_id" , id));
        return this.postRepo.findByCategory(category).stream().map(val->this.modelMapper.map(val,PostResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PageResponse searchPosts(String searchText) {
     List<Post> posts = this.postRepo.findByTitleContaining(searchText);
//        List<Post> posts = this.postRepo.searchByTitle("%"+searchText+"%");
        PageResponse pg = new PageResponse();
        pg.setContent(posts.stream().map(val->this.modelMapper.map(val,PostResponseDTO.class)).collect(Collectors.toList()));
        pg.setTotalElements(pg.getContent().size());
     return pg;
    }
}
