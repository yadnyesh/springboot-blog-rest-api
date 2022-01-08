package io.yadnyesh.springbootblog.service;

import io.yadnyesh.springbootblog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
