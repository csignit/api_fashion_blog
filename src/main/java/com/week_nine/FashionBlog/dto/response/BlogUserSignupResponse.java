package com.week_nine.FashionBlog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogUserSignupResponse {
    private Long id;
    private String name;
    private String email;

}
