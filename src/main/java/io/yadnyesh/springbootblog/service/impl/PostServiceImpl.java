package io.yadnyesh.springbootblog.service.impl;

import io.yadnyesh.springbootblog.entity.Post;
import io.yadnyesh.springbootblog.exception.ResourceNotFoundException;
import io.yadnyesh.springbootblog.payload.PostDto;
import io.yadnyesh.springbootblog.payload.PostResponse;
import io.yadnyesh.springbootblog.config.repository.PostRepository;
import io.yadnyesh.springbootblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private ModelMapper modelMapper;

    private PostRepository postRepository;

    public PostServiceImpl(ModelMapper modelMapper, PostRepository postRepository) {
        this.modelMapper = modelMapper;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(pageNo);
        postResponse.setPageSize(pageSize);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
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

    @Override
    public void deletePostId(Long postId) {
        Post currentPost = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        postRepository.delete(currentPost);
    }

    private PostDto mapToPostDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToPostEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
        return post;
    }
}
