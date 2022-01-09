package io.yadnyesh.springbootblog.config.repository;

import io.yadnyesh.springbootblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPostId(long postId);
}
