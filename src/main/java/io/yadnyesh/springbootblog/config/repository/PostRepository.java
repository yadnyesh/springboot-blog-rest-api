package io.yadnyesh.springbootblog.config.repository;

import io.yadnyesh.springbootblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
