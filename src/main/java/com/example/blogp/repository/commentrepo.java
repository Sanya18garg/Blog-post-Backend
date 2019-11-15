package com.example.blogp.repository;

import com.example.blogp.modal.blog;
import com.example.blogp.modal.comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface commentrepo extends JpaRepository<comments,Integer> {
    List<comments> findByBlog(blog b);

    comments findById(int commentid);

}
