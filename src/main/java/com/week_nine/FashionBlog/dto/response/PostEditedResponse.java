package com.week_nine.FashionBlog.dto.response;

import com.week_nine.FashionBlog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEditedResponse {
    private Long id;
    private String title;
    private String postContent;
    private Category category;
    private LocalDateTime editedAt;
}
