package com.jwctech.blogapi.payload;

import com.jwctech.blogapi.entity.Comment;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Set;

@Data
public class PostPayload {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have 2 characters.")
    private String title;
    @NotEmpty
    @Size(min = 2, message = "Post description should have 2 characters.")
    private String description;
    @NotEmpty(message = "Content should not be empty.")
    private String content;
    private Set<CommentPayload> comments;
}