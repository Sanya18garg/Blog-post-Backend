package com.example.blogp.controller;

import com.example.blogp.modal.user;
import com.example.blogp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
public class userController {
    @Autowired
    userRepository p1;

    @GetMapping("/validateUser")
    public String validateuser()
    {
        return "\"user validated\"";
    }
    @PostMapping("/addUsers")
    public user addusers(@Valid @RequestBody user user )
    {
        return p1.save(user);
    }
    @GetMapping("/getUsers")
    public List<user> getusers()
    {
        return p1.findAll();
    }

}
