package com.week_nine.FashionBlog.service.serviceImplementation;

import com.week_nine.FashionBlog.dto.request.BlogUserCommentRequest;
import com.week_nine.FashionBlog.dto.request.BlogUserLoginRequest;
import com.week_nine.FashionBlog.dto.request.BlogUserSignupRequest;
import com.week_nine.FashionBlog.dto.request.PostRequest;
import com.week_nine.FashionBlog.dto.response.*;
import com.week_nine.FashionBlog.entity.*;
import com.week_nine.FashionBlog.exception.MyCustomException;
import com.week_nine.FashionBlog.repository.BlogUserRepository;
import com.week_nine.FashionBlog.repository.CommentRepository;
import com.week_nine.FashionBlog.repository.PostRepository;
import com.week_nine.FashionBlog.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogUserImplementation implements BlogUserService {
    private final BlogUserRepository blogUserRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    @Override
    public BlogUserSignupResponse signupBlogUser(BlogUserSignupRequest blogUserSignupRequest) {
        Optional<BlogUser> blogUser = blogUserRepository.findBlogUserByEmail(blogUserSignupRequest.getEmail());
        if (blogUser.isPresent()) {
            throw new MyCustomException("User already exist");
        }
        BlogUser newBlogUser = BlogUser.builder()
                .name(blogUserSignupRequest.getName())
                .email(blogUserSignupRequest.getEmail())
                .password(blogUserSignupRequest.getPassword())
                .build();

        BlogUser saveBlogUser = blogUserRepository.save(newBlogUser);
        return BlogUserSignupResponse.builder()
                .id(saveBlogUser.getId())
                .email(saveBlogUser.getEmail())
                .name(saveBlogUser.getName())
                .build();
    }


    @Override
    public BlogUserLoginResponse loginBlogUser(BlogUserLoginRequest blogUserLoginRequest, HttpServletRequest request) {
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserByEmail(blogUserLoginRequest.getEmail());
        if (optionalBlogUser.isEmpty()) {
            throw new MyCustomException("Invalid request, please enter the right credentials");
        }
        BlogUser blogUser = optionalBlogUser.get();
        if (!blogUserLoginRequest.getPassword().equals(blogUser.getPassword())) {
            throw new MyCustomException("Invalid email or password");
        } else {
            if (!blogUserLoginRequest.getEmail().equals(blogUser.getEmail())){
                throw new MyCustomException("Invalid email or password");
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("BlogUser", blogUser);
        return BlogUserLoginResponse.builder()
                .id(blogUser.getId())
                .email(blogUser.getEmail())
                .message("Welcome " + blogUser.getName())
                .build();
    }

    @Override
    public BlogUserCommentResponse makeComment(BlogUserCommentRequest blogUserCommentRequest, Long postId, Long blogUserId) {
        Optional<Post> optionalPost = postRepository.findPostById(postId);
        if (optionalPost.isEmpty()){
            throw  new MyCustomException("Sorry, the post doesn't exist");
        }
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserById(blogUserId);
        if (optionalBlogUser.isEmpty()){
            throw new MyCustomException("Sorry, the BlogUser doesn't exist");
        }
        Post post = optionalPost.get();
        BlogUser blogUser = optionalBlogUser.get();
        // Creating new comment
        Comment newComment = Comment.builder()
                .content(blogUserCommentRequest.getComment())
                .post(post)
                .blogUsers(blogUser)
                .build();
        Comment savedComment = commentRepository.save(newComment);
        return BlogUserCommentResponse.builder()
                .post(post)
                .comment(savedComment.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        }

    @Override
    public BlogUserLikeResponse likePost(Long postId, Long blogUserId) {
        Optional<Post> optionalPost = postRepository.findPostById(postId);
        if (optionalPost.isEmpty()){
            throw new MyCustomException("Sorry, the post doesn't exist");
        }
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserById(blogUserId);
        if (optionalBlogUser.isEmpty()){
            throw new MyCustomException("Sorry, the BlogUser doesn't exist");
        }
        Post post = optionalPost.get();
        BlogUser blogUser = optionalBlogUser.get();

       // Check if the user has already liked the post
        boolean hasLiked = post.getLikes().stream()
                .anyMatch(like -> like.getBlogUser().getId().equals(blogUserId));
        if (hasLiked) {
            throw new MyCustomException("You have already liked this post");
        }
        // Create a new like
        Likes newLike = Likes.builder()
                .post(post)
                .blogUser(blogUser)
                .build();

        post.getLikes().add(newLike);
        blogUserRepository.save(blogUser);
        postRepository.save(post);

        return BlogUserLikeResponse.builder()
                .postId(postId)
                .blogUserId(blogUserId)
                .likedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public CommentDeleteResponse deleteComment(Long blogUserId, Long commentId) {
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserById(blogUserId);
        if (optionalBlogUser.isEmpty()){
            throw new MyCustomException("You are not authorized", HttpStatus.BAD_REQUEST);
        }
        BlogUser blogUser = optionalBlogUser.get();
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new MyCustomException("Sorry, Comment doesn't exist", HttpStatus.NO_CONTENT);
        }
        Comment comment = optionalComment.get();
        if (!blogUser.getId().equals(comment.getBlogUser().getId())) {
            throw new MyCustomException("You are not authorized to delete this comment", HttpStatus.CONFLICT);
        }

        commentRepository.delete(comment);

        return CommentDeleteResponse.builder()
                .message("Your comment has been deleted successfully")
                .build();
    }
}