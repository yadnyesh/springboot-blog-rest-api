package io.yadnyesh.springbootblog.service.impl;

import io.yadnyesh.springbootblog.entity.Comment;
import io.yadnyesh.springbootblog.entity.Post;
import io.yadnyesh.springbootblog.exception.BlogApiException;
import io.yadnyesh.springbootblog.exception.ResourceNotFoundException;
import io.yadnyesh.springbootblog.payload.CommentDto;
import io.yadnyesh.springbootblog.repository.CommentRepository;
import io.yadnyesh.springbootblog.repository.PostRepository;
import io.yadnyesh.springbootblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToCommentEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return mapToCommentDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findCommentByPostId(postId);
        return comments.stream().map(this::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                                  .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository
                                  .findById(commentId)
                                  .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToCommentDto(comment);
    }

    @Override
    public CommentDto updateCommntById(Long postId, Long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        Comment existingComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        if(!existingComment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        existingComment.setName(commentRequest.getName());
        existingComment.setBody(commentRequest.getBody());
        existingComment.setEmail(commentRequest.getEmail());
        Comment updatedComment = commentRepository.save(existingComment);
        return mapToCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        CommentDto existingCommentDto = getCommentById(postId, commentId);
        Comment existingComment = mapToCommentEntity(existingCommentDto);
        commentRepository.delete(existingComment);
    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);

//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    protected Comment mapToCommentEntity (CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
