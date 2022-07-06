package com.jwctech.blogapi.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CommentPayload {

    private Long id;
    @NotEmpty(message = "Name should not be empty.")
    private String name;
    @NotEmpty(message = "Email should not be empty.")
    @Email(message = "Should be in email format.")
    private String email;
    @NotEmpty(message = "Comment should not be empty.")
    private String body;
}
