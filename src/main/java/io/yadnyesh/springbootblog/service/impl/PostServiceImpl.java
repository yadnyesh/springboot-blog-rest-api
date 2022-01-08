package io.yadnyesh.springbootblog.service.impl;

import io.yadnyesh.springbootblog.entity.Post;
import io.yadnyesh.springbootblog.payload.PostDto;
import io.yadnyesh.springbootblog.repository.PostRepository;
import io.yadnyesh.springbootblog.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        BeanUtils.copyProperties(postDto, post);
        Post createdPost = postRepository.save(post);
        PostDto createdPostDtoResponse = new PostDto();
        BeanUtils.copyProperties(createdPost, createdPostDtoResponse);
        return createdPostDtoResponse;
    }
}
