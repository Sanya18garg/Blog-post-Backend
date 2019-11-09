package com.example.blogp.repository;


import com.example.blogp.modal.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface userRepository extends JpaRepository<user,Long> {
    //Optional<Users> findByusername(String name);

    user findByUserId(int  userid);

    Optional<user> findByUsername(String username);


    // public List<Users> findByCategory(String category);
    //public List<Users> findAllByProductPriceBetween(int p_price1,int p_price2);
    // ArrayList<Users> showUser();
    // Users setIsActive(int active);
    // String add(Users users);
}
