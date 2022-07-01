package com.jwctech.blogapi.payload;

import lombok.Data;

@Data
public class PostPayload {
    private Long id;
    private String description;
    private String title;
    private String content;

}
