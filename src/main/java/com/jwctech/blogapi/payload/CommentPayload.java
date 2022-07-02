package com.jwctech.blogapi.payload;

import lombok.Data;

@Data
public class CommentPayload {

    private Long id;
    private String name;
    private String email;
    private String body;
}
