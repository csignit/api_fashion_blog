package com.week_nine.FashionBlog.repository;


import com.week_nine.FashionBlog.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
    Optional<BlogUser> findBlogUserByEmail (String email);
    Optional<BlogUser> findBlogUserById (Long blogUserId);
}
