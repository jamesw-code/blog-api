package com.jwctech.blogapi.service;

import com.jwctech.blogapi.payload.PostPayload;

import java.util.List;

public interface PostService {
    PostPayload createPost(PostPayload PostPayload);

    List<PostPayload> getAllPost();
}
