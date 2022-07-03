package com.jwctech.blogapi.service.impl;

import com.jwctech.blogapi.entity.Post;
import com.jwctech.blogapi.exception.ResourceNotFoundException;
import com.jwctech.blogapi.payload.PostPayload;
import com.jwctech.blogapi.repository.PostRepo;
import com.jwctech.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepo postRepo, ModelMapper mapper) {
        this.postRepo = postRepo;
        this.mapper = mapper;
    }

    @Override
    public PostPayload createPost(PostPayload postPayload) {

        //Convert Payload to Entity
        Post post = mapToEntity(postPayload);
        //Create Entity
        Post newPost = postRepo.save(post);
        //Convert Post Entity to Payload
        PostPayload postResponse = mapToPayload(newPost);
        return postResponse;
    }

    @Override
    public List<PostPayload> getAllPost() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(post -> mapToPayload(post)).collect(Collectors.toList());
    }

    @Override
    public PostPayload getById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id.toString()));
        return mapToPayload(post);
    }

    @Override
    public PostPayload updatePost(PostPayload postPayload, Long id) {
        //Get Post by ID
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id.toString()));
        //Update from Payload
        post.setTitle(postPayload.getTitle());
        post.setDescription(postPayload.getDescription());
        post.setContent(postPayload.getContent());

        Post updatedPost = postRepo.save(post);

        return mapToPayload(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        //Get Post by ID
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id", id.toString()));
        postRepo.delete(post);
    }

    //Convert Post Entity to Payload
    private PostPayload mapToPayload(Post post) {
        PostPayload postPayload = mapper.map(post, PostPayload.class);
//        postPayload.setId(post.getId());
//        postPayload.setTitle(post.getTitle());
//        postPayload.setDescription(post.getDescription());
//        postPayload.setContent(post.getContent());
        return postPayload;
    }
    //Convert Payload to Entity
    private Post mapToEntity(PostPayload postPayload){
        Post post = mapper.map(postPayload, Post.class);
//        Post post = new Post();
//        post.setTitle(postPayload.getTitle());
//        post.setDescription(postPayload.getDescription());
//        post.setContent(postPayload.getContent());
        return post;
    }
}
