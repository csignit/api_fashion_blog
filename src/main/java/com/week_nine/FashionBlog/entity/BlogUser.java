package com.week_nine.FashionBlog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class BlogUser extends BaseEntity{

    @Column(length=100, nullable = false)
    private String name;

    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(length=254, nullable = false)
    @OneToMany
    @JoinColumn
    private List <Comment> comment;

    private Integer likes;
    @OneToMany
    @JoinColumn
    private List<Post> post;
}
