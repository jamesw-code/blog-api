package com.jwctech.blogapi.service.impl;

import com.jwctech.blogapi.entity.Comment;
import com.jwctech.blogapi.entity.Post;
import com.jwctech.blogapi.exception.BlogAPIException;
import com.jwctech.blogapi.exception.ResourceNotFoundException;
import com.jwctech.blogapi.payload.CommentPayload;
import com.jwctech.blogapi.repository.CommentRepo;
import com.jwctech.blogapi.repository.PostRepo;
import com.jwctech.blogapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private PostRepo postRepo;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo, ModelMapper mapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.mapper = mapper;
    }

    @Override
    public CommentPayload createComment(Long postId, CommentPayload commentPayload) {
        Comment comment = mapToEntity(commentPayload);
        //Retrieve Post entity by id
        Post post = findPostById(postId);
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

    @Override
    public CommentPayload getCommentById(Long postId, Long commentId) {
        //Retrieve Post entity by id
        Post post = findPostById(postId);
        //Retrieve Comment by id
        Comment comment = findCommentById(commentId);
        //Validate if Comment Belongs to Post
        validateIfCommentBelongsToPost(post, comment);

        return mapToPayload(comment);
    }

    @Override
    public CommentPayload updateComment(Long postId, Long commentId, CommentPayload commentRequest) {
        //Retrieve Post entity by id
        Post post = findPostById(postId);
        //Retrieve Comment by id
        Comment comment = findCommentById(commentId);
        //Validate if Comment Belongs to Post
        validateIfCommentBelongsToPost(post, comment);

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepo.save(comment);

        return mapToPayload(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        //Retrieve Post entity by id
        Post post = findPostById(postId);
        //Retrieve Comment by id
        Comment comment = findCommentById(commentId);
        //Validate if Comment Belongs to Post
        validateIfCommentBelongsToPost(post, comment);
        commentRepo.delete(comment);
    }

    public CommentPayload mapToPayload(Comment comment){
        CommentPayload commentPayload = mapper.map(comment, CommentPayload.class);
//        CommentPayload commentPayload = new CommentPayload();
//        commentPayload.setId(comment.getId());
//        commentPayload.setName(comment.getName());
//        commentPayload.setEmail(comment.getEmail());
//        commentPayload.setBody(comment.getBody());
        return commentPayload;
    }
    public Comment mapToEntity(CommentPayload commentPayload){
        Comment comment = mapper.map(commentPayload, Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentPayload.getId());
//        comment.setName(commentPayload.getName());
//        comment.setEmail(commentPayload.getEmail());
//        comment.setBody(commentPayload.getBody());
        return comment;
    }

    private Post findPostById(Long postId) {
        //Retrieve Post entity by id
        return postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post", "id", postId.toString()));
    }
    private Comment findCommentById(Long commentId){
        //Retrieve Comment by id
        return commentRepo.findById(commentId).orElseThrow(
                () ->new ResourceNotFoundException("Comment", "id", commentId.toString()));
    }
    private void validateIfCommentBelongsToPost(Post post, Comment comment){
        //Validate if Comment Belongs to Post TODO:BlogAPIException not working properly
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post.");
        }
    }
}
