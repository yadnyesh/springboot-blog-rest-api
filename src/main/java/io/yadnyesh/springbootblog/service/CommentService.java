package io.yadnyesh.springbootblog.service;

import io.yadnyesh.springbootblog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
