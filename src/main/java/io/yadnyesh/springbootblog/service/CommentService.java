package io.yadnyesh.springbootblog.service;

import io.yadnyesh.springbootblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId (long postId);
}
