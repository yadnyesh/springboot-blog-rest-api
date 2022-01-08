package io.yadnyesh.springbootblog.service;

import io.yadnyesh.springbootblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
}
