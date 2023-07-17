package com.week_nine.FashionBlog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogUserLoginRequest {

    private Long blogUserId;
    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required, please provide a valid email")
    private String email;

    @NotBlank(message = "Please, provide your password")
    private String password;
}
