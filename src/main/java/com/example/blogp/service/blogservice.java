package com.example.blogp.service;




import com.example.blogp.modal.blog;
import com.example.blogp.modal.user;
import com.example.blogp.repository.blogrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class blogservice {
    @Autowired
    private blogrepo BlogRepository;


    @Autowired
    private com.example.blogp.repository.userRepository userRepository;


    public blog addblog(blog blog, int  userid) {
        user user = userRepository.findByUserId(userid);
        blog.setUser(user);
        return BlogRepository.save(blog);
    }



    public List<blog> getBlogList(){
        return BlogRepository.findAll();

    }

   public List<blog> getBlogById(int id){
        return BlogRepository.findById(id);
    }

    public List<blog> getBlogByUserId(int id){
        user u=userRepository.findByUserId(id);
        return BlogRepository.findByUser(u);
    }

    public blog update(blog b){
       int blogid= b.getBlogid();
        blog blog=BlogRepository.findByBlogid(blogid);
        System.out.println(blog);
        blog.setBody(b.getBody());
        blog.setTitle(b.getTitle());
        blog.setCreateDate(b.getCreateDate());
        return BlogRepository.save(blog);
    }
    public List<blog> deleteblog(int userid, int blogid)
    {
        blog blog1=BlogRepository.findByBlogid(blogid);
        user users=userRepository.findByUserId(userid);
        BlogRepository.delete(blog1);
        return BlogRepository.findByUser(users);

    }
}
