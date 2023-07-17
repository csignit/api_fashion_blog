package com.week_nine.FashionBlog.controller;

import com.week_nine.FashionBlog.dto.request.*;
import com.week_nine.FashionBlog.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogUserController {

    private final BlogUserService blogUserService;

    @PostMapping("/signup/blogUser")
    public ResponseEntity<?> createBlogUser (@RequestBody @Valid BlogUserSignupRequest blogUserSignupRequest){
        var response = blogUserService.signupBlogUser(blogUserSignupRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/blogUser")
    public ResponseEntity<?> loginBlogUser (@RequestBody BlogUserLoginRequest blogUserLoginRequest, HttpServletRequest request){
        var response = blogUserService.loginBlogUser(blogUserLoginRequest, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createComment/{postId}/{blogUser}")
    public ResponseEntity<?> makeComment (@RequestBody @Valid BlogUserCommentRequest blogUserCommentRequest,
                                          @PathVariable ("postId") Long postId, @PathVariable ("blogUser") Long blogUserId ){
        var response = blogUserService.makeComment(blogUserCommentRequest, postId, blogUserId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/likePost/{PostId}/{BlogUserId}")
    public ResponseEntity<?> likePost (@PathVariable("PostId") Long postId, @PathVariable("BlogUserId") Long blogUserId){
        var response = blogUserService.likePost(postId, blogUserId);
        return ResponseEntity.ok(response);
    }

        @DeleteMapping("/deleteComment/{BlogUserId}/{CommentId}")
    public ResponseEntity<?> deleteComment (@PathVariable("BlogUserId")Long blogUserId, @PathVariable("CommentId") Long commentId){
        var response = blogUserService.deleteComment(blogUserId, commentId);
        return ResponseEntity.ok(response);
    }
}
