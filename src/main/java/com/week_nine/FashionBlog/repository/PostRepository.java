package com.week_nine.FashionBlog.repository;

import com.week_nine.FashionBlog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostById (Long postId);
}
