package io.yadnyesh.springbootblog.service.impl;

import io.yadnyesh.springbootblog.entity.Post;
import io.yadnyesh.springbootblog.exception.ResourceNotFoundException;
import io.yadnyesh.springbootblog.payload.PostDto;
import io.yadnyesh.springbootblog.repository.PostRepository;
import io.yadnyesh.springbootblog.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToPostEntity(postDto);

        Post createdPost = postRepository.save(post);

        PostDto createdPostDtoResponse = mapToPostDto(createdPost);

        return createdPostDtoResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepository
                     .findById(postId)
                     .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        return mapToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post currentPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        currentPost.setTitle(postDto.getTitle());
        currentPost.setDescription(postDto.getDescription());
        currentPost.setContent(postDto.getContent());
        return mapToPostDto(postRepository.save(currentPost));
    }

    private PostDto mapToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToPostEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}
