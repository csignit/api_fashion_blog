package com.week_nine.FashionBlog.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class Comment extends BaseEntity {

    @ManyToOne
    private BlogUser blogUser;
    @ManyToOne
    private Post post;

    private String content;

    private LocalDateTime createdAt;
    @OneToMany
    @JoinColumn
//    private List <Likes> likes = new ArrayList<>();
    private List <Likes> likes;

    @ManyToOne(optional = false)
    @JoinColumn
    private BlogUser blogUsers;

    public BlogUser getBlogUsers() {
        return blogUsers;
    }

    public void setBlogUsers(BlogUser blogUsers) {
        this.blogUsers = blogUsers;
    }
}
