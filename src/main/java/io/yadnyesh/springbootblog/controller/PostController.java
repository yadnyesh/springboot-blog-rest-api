package io.yadnyesh.springbootblog.controller;

import io.yadnyesh.springbootblog.payload.PostDto;
import io.yadnyesh.springbootblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(postService.getPostById(Long.parseLong(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") String id) {
        return new ResponseEntity<>(postService.updatePost(postDto, Long.parseLong(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") String id) {
        postService.deletePostId(Long.parseLong(id));
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
