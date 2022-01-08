package io.yadnyesh.springbootblog.repository;

import io.yadnyesh.springbootblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
