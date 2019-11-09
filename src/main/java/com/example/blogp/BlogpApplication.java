package com.example.blogp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude ={SecurityAutoConfiguration.class})
@EnableJpaAuditing
public class BlogpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogpApplication.class, args);
    }

}
