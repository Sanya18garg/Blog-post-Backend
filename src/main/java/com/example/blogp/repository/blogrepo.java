package com.example.blogp.repository;

import com.example.blogp.modal.blog;
import com.example.blogp.modal.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface blogrepo extends JpaRepository<blog,Integer> {




        List<blog>  findById(int blogid);
        List<blog> findByUser(user user);
        blog findByBlogid(int blogid);

    }


