package com.example.blogp.service;

import com.example.blogp.modal.user;
import com.example.blogp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class currentuser {
    @Autowired
    userRepository u;

    public user getpro(Principal principal) {
        Optional<user> my = u.findByUsername(principal.getName());
        return my.get();
    }
}