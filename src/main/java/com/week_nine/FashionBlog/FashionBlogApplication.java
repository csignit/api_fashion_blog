package com.week_nine.FashionBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FashionBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionBlogApplication.class, args);
	}

}
