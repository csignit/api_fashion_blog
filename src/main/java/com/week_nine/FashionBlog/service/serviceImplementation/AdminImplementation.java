package com.week_nine.FashionBlog.service.serviceImplementation;

import com.week_nine.FashionBlog.dto.request.AdminLoginRequest;
import com.week_nine.FashionBlog.dto.request.AdminSignupRequest;
import com.week_nine.FashionBlog.dto.request.PostEditRequest;
import com.week_nine.FashionBlog.dto.request.PostRequest;
import com.week_nine.FashionBlog.dto.response.*;
import com.week_nine.FashionBlog.entity.Admin;
import com.week_nine.FashionBlog.entity.Post;
import com.week_nine.FashionBlog.exception.MyCustomException;
import com.week_nine.FashionBlog.repository.AdminRepository;
import com.week_nine.FashionBlog.repository.PostRepository;
import com.week_nine.FashionBlog.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminImplementation implements AdminService {
    private final AdminRepository adminRepository;
    private final PostRepository postRepository;
    @Override
    public AdminSignupResponse signupAdmin(AdminSignupRequest adminSignupRequest) {
        Optional<Admin> admin = adminRepository.findAdminByEmail(adminSignupRequest.getEmail());
        if (admin.isPresent()){
            throw new MyCustomException("Admin already exist");
        }
        Admin newAdmin = Admin.builder()
                .email(adminSignupRequest.getEmail())
                .name(adminSignupRequest.getName())
                .password(adminSignupRequest.getPassword())
                .build();

        Admin saveAdmin = adminRepository.save(newAdmin);
        return AdminSignupResponse.builder()
                .id(saveAdmin.getId())
                .email(saveAdmin.getEmail())
                .name(saveAdmin.getName())
                .build();
    }

    @Override
    public AdminLoginResponse loginAdmin(AdminLoginRequest adminLoginRequest, HttpServletRequest request) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminByEmail(adminLoginRequest.getEmail());
        if (optionalAdmin.isEmpty()){
            throw new MyCustomException("Invalid request, please enter the right credentials");
        }
        Admin admin = optionalAdmin.get();
        if (!adminLoginRequest.getPassword().equals(admin.getPassword())){
            throw new MyCustomException("Invalid email or password");
        }
        HttpSession session = request.getSession();
        session.setAttribute("Admin", admin);
        return AdminLoginResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .message("Admin " + admin.getName() + " You are welcome!")
                .build();
    }

    @Override
    public PostResponse createPost(PostRequest postRequest, Long adminId) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminById(adminId);
        if (optionalAdmin.isEmpty()){
            throw new MyCustomException("Sorry, Admin doesn't not exist");
        }
        Admin admin = optionalAdmin.get();
        // Creating new post
        Post newPost = Post.builder()
                .title(postRequest.getTitle())
                .postContent(postRequest.getPostContent())
                .category(postRequest.getCategory())
                .createdAt(LocalDateTime.now())
                .admin(admin)
                .build();

        Post savedPost = postRepository.save(newPost);
        return PostResponse.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .category(savedPost.getCategory())
                .postContent(savedPost.getPostContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    public PostEditedResponse editPost(PostEditRequest postEditRequest, Long adminId, Long postId) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminById(adminId);
        if (optionalAdmin.isEmpty()) {
            throw new MyCustomException("You are not an Admin", HttpStatus.BAD_REQUEST);
        } else {
            Admin admin = optionalAdmin.get();
            Optional<Post> optionalPost = postRepository.findPostById(postId);
            if (optionalPost.isEmpty()) {
                throw new MyCustomException("Sorry, Post doesn't exist", HttpStatus.NO_CONTENT);
            } else {
                Post post = optionalPost.get();
                if (!admin.getId().equals(post.getAdmin().getId())) {
                    throw new MyCustomException("You are not authorized to edit this post", HttpStatus.CONFLICT);
                }
                post.setTitle(postEditRequest.getTitle());
                post.setPostContent(postEditRequest.getPostContent());
                post.setCategory(postEditRequest.getCategory());
                Post editedPost = postRepository.save(post);
                return PostEditedResponse.builder()
                        .id(editedPost.getId())
                        .title(editedPost.getTitle())
                        .postContent(editedPost.getPostContent())
                        .category(editedPost.getCategory())
                        .editedAt(LocalDateTime.now())
                        .build();
            }
        }
    }

    @Override
    public PostDeleteResponse deletePost(Long adminId, Long postId) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminById(adminId);
        if (optionalAdmin.isEmpty()) {
            throw new MyCustomException("You are not an Admin", HttpStatus.BAD_REQUEST);
        }
        Admin admin = optionalAdmin.get();
        Optional<Post> optionalPost = postRepository.findPostById(postId);
        if (optionalPost.isEmpty()) {
            throw new MyCustomException("Sorry, Post doesn't exist", HttpStatus.NO_CONTENT);
        }
        Post post = optionalPost.get();
        if (!admin.getId().equals(post.getAdmin().getId())) {
            throw new MyCustomException("You are not authorized to delete this post", HttpStatus.CONFLICT);
        }
        postRepository.delete(post);
        return PostDeleteResponse.builder()
                .message("Your post has been deleted successfully")
                .build();
    }



}
