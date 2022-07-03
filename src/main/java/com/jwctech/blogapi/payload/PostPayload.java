package com.jwctech.blogapi.payload;

import com.jwctech.blogapi.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostPayload {
    private Long id;
    private String description;
    private String title;
    private String content;
    private Set<CommentPayload> comments;

}
