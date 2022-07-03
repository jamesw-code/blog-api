package com.jwctech.blogapi.service.impl;

import com.jwctech.blogapi.entity.Comment;
import com.jwctech.blogapi.entity.Post;
import com.jwctech.blogapi.exception.ResourceNotFoundException;
import com.jwctech.blogapi.payload.CommentPayload;
import com.jwctech.blogapi.repository.CommentRepo;
import com.jwctech.blogapi.repository.PostRepo;
import com.jwctech.blogapi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private PostRepo postRepo;

    public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentPayload createComment(Long postId, CommentPayload commentPayload) {
        Comment comment = mapToEntity(commentPayload);

        //Retrieve Post entity by id
        Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post", "id", postId.toString()));

        // Set Post to comment Entity
        comment.setPost(post);

        // Comment Entity to DB
        Comment newComment = commentRepo.save(comment);

        return mapToPayload(newComment);
    }

    @Override
    public List<CommentPayload> getCommentsByPostId(Long postId) {
        //Retrieve Comments by postId
        List<Comment> comments = commentRepo.findByPostId(postId);

        return comments.stream().map(comment -> mapToPayload(comment)).collect(Collectors.toList());

    }

    public CommentPayload mapToPayload(Comment comment){
        CommentPayload commentPayload = new CommentPayload();
        commentPayload.setId(comment.getId());
        commentPayload.setName(comment.getName());
        commentPayload.setEmail(comment.getName());
        commentPayload.setBody(comment.getBody());
        return commentPayload;
    }
    public Comment mapToEntity(CommentPayload commentPayload){
        Comment comment = new Comment();
        comment.setId(commentPayload.getId());
        comment.setName(commentPayload.getName());
        comment.setEmail(commentPayload.getName());
        comment.setBody(commentPayload.getBody());
        return comment;
    }
}
