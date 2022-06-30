package com.example.easynotes.repository;

import com.example.easynotes.model.Category;
import com.example.easynotes.model.Post;
import com.example.easynotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String searchText);

    @Query("Select p from Post p where p.title like :searchKey")
    List<Post> searchByTitle(@Param("searchKey") String title);

}
