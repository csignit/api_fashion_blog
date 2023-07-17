package com.week_nine.FashionBlog.dto.request;

import com.week_nine.FashionBlog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Post content is required")
    private String postContent;
    private Category category;
}
