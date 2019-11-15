package com.example.blogp.controller;

import com.example.blogp.modal.blog;
import com.example.blogp.modal.user;
import com.example.blogp.repository.userRepository;
import com.example.blogp.service.blogservice;
import com.example.blogp.service.currentuser;
import com.example.blogp.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/profile")
public class profile {
    @Autowired
    currentuser c;
    @Autowired
    userRepository u;
    @Autowired
    com.example.blogp.service.userservice userservice;
    @Autowired
    blogservice blogService;

    @GetMapping("/get")
    public user getdata(Principal principal) {
        return c.getpro(principal);
    }

    @PutMapping("/update")
    public user update(@Valid @RequestBody user users) {
        users.setActive(1);
        u.save(users);
        return users;
    }

    @GetMapping("/follow/{userid}")
    public user follows(@PathVariable("userid") int userid, Principal principal) {
        c.follow(userid, principal);
        return c.getpro(principal);
    }

    @GetMapping("/unfollow/{userid}")
    public user unfollow(@PathVariable("userid") int userid, Principal principal) {
        c.unfollow(userid, principal);
        return c.getpro(principal);
    }

    @GetMapping("/getblogsoffollowing")
    public List<blog> getblogs(Principal principal) {
        return c.getblogsoffollowing(principal);
    }

    @GetMapping("/get/{id}")
    public user getdata(@PathVariable("id") int id){
        user user=u.findByUserId(id);
        return user;
    }
    @GetMapping(path = "search/{keyword}", produces = "application/json")
    public List<blog> getSearchResult(@PathVariable("keyword") String keyword , Principal principal) {
        return blogService.searchResult(keyword,principal);
    }

}