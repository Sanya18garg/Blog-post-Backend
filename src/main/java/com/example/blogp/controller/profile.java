package com.example.blogp.controller;

import com.example.blogp.modal.user;
import com.example.blogp.repository.userRepository;
import com.example.blogp.service.currentuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/profile")
public class profile {
    @Autowired
    currentuser c;
    @Autowired
    userRepository u;
    @GetMapping("/get")
    public user getdata(Principal principal)
    {
        return c.getpro(principal);
    }
    @PutMapping("/update")
    public user update(@Valid @RequestBody user users)
    {
        users.setActive(1);
        u.save(users);
        return users;
    }
}

