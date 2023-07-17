package com.week_nine.FashionBlog.controller;

import com.week_nine.FashionBlog.dto.request.AdminLoginRequest;
import com.week_nine.FashionBlog.dto.request.AdminSignupRequest;
import com.week_nine.FashionBlog.dto.request.PostEditRequest;
import com.week_nine.FashionBlog.dto.request.PostRequest;
import com.week_nine.FashionBlog.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/signup/admin")
    public ResponseEntity<?> createAdmin (@RequestBody @Valid AdminSignupRequest adminSignupRequest){
        var response = adminService.signupAdmin(adminSignupRequest);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/login/admin")
    public  ResponseEntity<?> loginAdmin (@RequestBody AdminLoginRequest adminLoginRequest, HttpServletRequest request){
        var response = adminService.loginAdmin(adminLoginRequest, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createPost/{AdminId}")
    public ResponseEntity<?> createPost (@RequestBody @Valid PostRequest postRequest, @PathVariable("AdminId") Long adminId){
        var response = adminService.createPost(postRequest, adminId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/editPost/{AdminId}/{PostId}")
    public ResponseEntity<?> editPost (@RequestBody @Valid PostEditRequest postEditRequest, @PathVariable("AdminId") Long adminId,
                                       @PathVariable ("PostId")Long postId){
        var response = adminService.editPost(postEditRequest, adminId, postId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteTask/{AdminId}/{PostId}")
    public  ResponseEntity<?> deletePost (@PathVariable("AdminId")Long adminId, @PathVariable("PostId")Long postId){
        var response = adminService.deletePost(adminId, postId);
        return ResponseEntity.ok(response);
    }
}


