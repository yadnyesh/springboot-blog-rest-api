package io.yadnyesh.springbootblog.service.impl;

import io.yadnyesh.springbootblog.entity.Comment;
import io.yadnyesh.springbootblog.entity.Post;
import io.yadnyesh.springbootblog.exception.ResourceNotFoundException;
import io.yadnyesh.springbootblog.payload.CommentDto;
import io.yadnyesh.springbootblog.repository.CommentRepository;
import io.yadnyesh.springbootblog.repository.PostRepository;
import io.yadnyesh.springbootblog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToCommentEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return mapToCommentDto(savedComment);
    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    protected Comment mapToCommentEntity (CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
