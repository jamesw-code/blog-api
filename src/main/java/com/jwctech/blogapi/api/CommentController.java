package com.jwctech.blogapi.api;

import com.jwctech.blogapi.payload.CommentPayload;

import com.jwctech.blogapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //Create comment
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentPayload> createComment(
            @PathVariable(value = "postId") Long postId,
            @RequestBody CommentPayload commentPayload) {
        return new ResponseEntity<>(commentService.createComment(postId, commentPayload),
                HttpStatus.CREATED);
    }
    //Get all Comments
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentPayload>> getCommentsByPostId(@PathVariable(name = "postId")Long postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),
                HttpStatus.OK);
    }
    //Get Comment
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentPayload> getCommentById(
            @PathVariable(name = "postId")Long postId,
            @PathVariable(name = "commentId")Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId),
                HttpStatus.OK);
    }
    //Update Comment
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentPayload> updateComment(
            @PathVariable(name = "postId")Long postId,
            @PathVariable(name = "commentId")Long commentId,
            @RequestBody CommentPayload commentPayload){
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentPayload), HttpStatus.OK);
    }
    //Delete Comment
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable(name = "postId")Long postId,
            @PathVariable(name = "commentId")Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Delete Successful.", HttpStatus.OK);
    }

}
