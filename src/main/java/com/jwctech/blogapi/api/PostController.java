package com.jwctech.blogapi.api;

import com.jwctech.blogapi.payload.PostPayload;
import com.jwctech.blogapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create Blog Post Rest API
    @PostMapping
    public ResponseEntity<PostPayload> createPost(@RequestBody PostPayload postPayload) {
        return new ResponseEntity<>(postService.createPost(postPayload), HttpStatus.CREATED);
    }
    //Get All Post Rest API
    @GetMapping
    public ResponseEntity<List<PostPayload>> getAllPost(){
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }
}
