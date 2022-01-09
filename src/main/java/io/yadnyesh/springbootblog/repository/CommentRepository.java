package io.yadnyesh.springbootblog.repository;

import io.yadnyesh.springbootblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPostId(long postId);
}
