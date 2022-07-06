package com.jwctech.blogapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentPayload {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String body;
}
