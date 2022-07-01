package com.jwctech.blogapi.service.impl;

import com.jwctech.blogapi.entity.Post;
import com.jwctech.blogapi.payload.PostPayload;
import com.jwctech.blogapi.repository.PostRepo;
import com.jwctech.blogapi.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostPayload createPost(PostPayload postPayload) {

        //Convert Payload to Entity
        Post post = new Post();
        post.setTitle(postPayload.getTitle());
        post.setDescription(postPayload.getDescription());
        post.setContent(postPayload.getContent());

        //Convert Post Entity to Payload
        Post newPost = postRepo.save(post);
        PostPayload postResponse = new PostPayload();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());
        return postResponse;
    }
}
