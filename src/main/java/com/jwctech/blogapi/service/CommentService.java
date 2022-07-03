package com.jwctech.blogapi.service;

import com.jwctech.blogapi.payload.CommentPayload;

import java.util.List;

public interface CommentService {

    CommentPayload createComment(Long postId, CommentPayload commentPayload);

    List<CommentPayload> getCommentsByPostId(Long postId);

    CommentPayload getCommentById(Long postId, Long commentId);
}
