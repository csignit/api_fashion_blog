package com.week_nine.FashionBlog.dto.response;

import com.week_nine.FashionBlog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogUserCommentResponse {
    private Post post;
    private String comment;
    private LocalDateTime createdAt;
}

