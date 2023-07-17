package com.week_nine.FashionBlog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class Admin extends BaseEntity{
    @Column(nullable = false)
    private String email;
    @NotBlank(message = "Please provide your name")
    private String name;
    @Column(nullable = false)
    private String password;

    @OneToMany
    @JoinColumn
    private List <Post> post;

}
