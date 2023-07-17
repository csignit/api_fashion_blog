package com.week_nine.FashionBlog.service;

import com.week_nine.FashionBlog.dto.request.AdminLoginRequest;
import com.week_nine.FashionBlog.dto.request.AdminSignupRequest;
import com.week_nine.FashionBlog.dto.request.PostEditRequest;
import com.week_nine.FashionBlog.dto.request.PostRequest;
import com.week_nine.FashionBlog.dto.response.*;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    AdminSignupResponse signupAdmin (AdminSignupRequest adminSignupRequest);
    AdminLoginResponse loginAdmin (AdminLoginRequest adminLoginRequest, HttpServletRequest request);
    PostResponse createPost (PostRequest postRequest, Long adminId);
    PostEditedResponse editPost (PostEditRequest postEditRequest, Long adminId, Long postId);
    PostDeleteResponse deletePost (Long adminId, Long postId);



}
