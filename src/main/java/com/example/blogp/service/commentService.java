package com.example.blogp.service;

import com.example.blogp.modal.blog;
import com.example.blogp.modal.comments;
import com.example.blogp.modal.user;
import com.example.blogp.repository.blogrepo;
import com.example.blogp.repository.commentrepo;
import com.example.blogp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentService {
    @Autowired
    private com.example.blogp.repository.userRepository userRepository;
    @Autowired
    private blogrepo BlogRepository;
    @Autowired
    private commentrepo CommentRepository;
    public comments addcomment(comments comments, int blogid, int  userid) {
        user user = userRepository.findByUserId(userid);
        blog blog =BlogRepository.findByBlogid(blogid);
        comments.setUser(user);
        comments.setBlog(blog);
        return CommentRepository.save(comments);
    }

    public List<comments> getList(){
        return CommentRepository.findAll();

    }

    public List<comments> getbyblog(int id){
        blog b=BlogRepository.findByBlogid(id);
        return CommentRepository.findByBlog(b);
    }

    public List<comments> deletecomment(int commentid)
    {
        comments comment =CommentRepository.findById(commentid);
        blog b=comment.getBlog();
        CommentRepository.delete(comment);
        return CommentRepository.findByBlog(b);

    }
}
