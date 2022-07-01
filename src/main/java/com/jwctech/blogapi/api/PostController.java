package com.jwctech.blogapi.api;

import com.jwctech.blogapi.payload.PostPayload;
import com.jwctech.blogapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create Blog Post
    @PostMapping
    public ResponseEntity<PostPayload> createPost(@RequestBody PostPayload postPayload) {
        return new ResponseEntity<>(postService.createPost(postPayload), HttpStatus.CREATED);
    }
}
