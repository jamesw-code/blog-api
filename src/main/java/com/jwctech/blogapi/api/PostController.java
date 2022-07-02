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
    //Get Post by ID Rest API
    @GetMapping("/{id}")
    public ResponseEntity<PostPayload> getPostById(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }
    //Update Post by ID Rest API
    @PutMapping("/{id}")
    public ResponseEntity<PostPayload> updatePostById(
            @RequestBody PostPayload postPayload,
            @PathVariable(name="id")Long id){
        return new ResponseEntity<>(postService.updatePost(postPayload, id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable(name="id")Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post Delete Successful!");
    }
}
