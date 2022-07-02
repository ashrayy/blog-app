package com.example.easynotes.payload;

import com.example.easynotes.model.Category;
import com.example.easynotes.model.Comment;
import com.example.easynotes.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDTO {

    private Integer id;
    private String title;
    private String content;
    private CategoryDTO category;
    private UserDTO user;
    private String imageName;
    private Date addedDate;

    private Set<Comment> comments = new HashSet<>();
    //    private String imageName= "default.png";


}
