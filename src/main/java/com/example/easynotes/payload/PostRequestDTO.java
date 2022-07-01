package com.example.easynotes.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDTO {

    private String title;
    private String content;
    private Integer user_id;
    private Integer category_id;
    private String imageName;


}
