package io.yadnyesh.springbootblog.service;

import io.yadnyesh.springbootblog.payload.PostDto;
import io.yadnyesh.springbootblog.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long postId);
    PostDto updatePost(PostDto postDto, Long postId);
    void deletePostId(Long postId);
}
