package com.week_nine.FashionBlog.service;

import com.week_nine.FashionBlog.dto.request.BlogUserCommentRequest;
import com.week_nine.FashionBlog.dto.request.BlogUserLoginRequest;
import com.week_nine.FashionBlog.dto.request.BlogUserSignupRequest;
import com.week_nine.FashionBlog.dto.response.*;

import javax.servlet.http.HttpServletRequest;

public interface BlogUserService {
    BlogUserSignupResponse signupBlogUser (BlogUserSignupRequest blogUserSignupRequest);
    BlogUserLoginResponse loginBlogUser (BlogUserLoginRequest blogUserLoginRequest, HttpServletRequest request);
    BlogUserCommentResponse makeComment (BlogUserCommentRequest blogUserCommentRequest, Long postId, Long blogUserId);
    BlogUserLikeResponse likePost (Long postId, Long blogUserId);
    CommentDeleteResponse deleteComment (Long blogUserId, Long commentId);





}
