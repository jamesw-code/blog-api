package com.jwctech.blogapi.service;

import com.jwctech.blogapi.payload.CommentPayload;

public interface CommentService {

    CommentPayload createComment(Long postId, CommentPayload commentPayload);
}
