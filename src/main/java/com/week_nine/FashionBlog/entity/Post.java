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
public class Post extends BaseEntity {

    private String title;

    private String postContent;
    @Enumerated (EnumType.STRING)
    private Category category;

    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    @OneToMany
    @JoinColumn
    private List <Likes> likes;

    @OneToMany
    @JoinColumn
    private List<Comment> comment = new ArrayList<>();
    @ManyToOne
    @JoinColumn
    private Admin admin;
}
