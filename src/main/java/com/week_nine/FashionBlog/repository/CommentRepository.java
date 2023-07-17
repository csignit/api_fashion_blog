package com.week_nine.FashionBlog.repository;

import com.week_nine.FashionBlog.entity.Admin;
import com.week_nine.FashionBlog.entity.BlogUser;
import com.week_nine.FashionBlog.entity.Comment;
import com.week_nine.FashionBlog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllPostById (Post postId, BlogUser blogUser);
}
