package com.example.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDTO {

    private int id;
    private String content;
    private int user_id;
    private int category_id;
    private int comment_id;
    private int post_id;
}
