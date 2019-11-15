package com.example.blogp.service;

import com.example.blogp.modal.blog;
import com.example.blogp.modal.user;
import com.example.blogp.repository.blogrepo;
import com.example.blogp.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class currentuser {
    @Autowired
    userRepository u;
    @Autowired
    blogrepo BlogRepository;

    public user getpro(Principal principal) {
        Optional<user> my = u.findByUsername(principal.getName());
        return my.get();
    }
    public  user follow(int userid, Principal principal){
        user user=u.findByUserId(userid);
        String name=principal.getName();
        int  principalid = u.findByUsername(name).get().getUserId();
        user currentuser=u.findByUserId(principalid);
        ArrayList<Integer> arr=user.getFollowers();
        arr.add(principalid);
        user.setFollowers(arr);
        ArrayList<Integer> arr2=currentuser.getFollowing();
        arr2.add(userid);
        currentuser.setFollowing(arr2);
        u.save(user);
        u.save(currentuser);
        Optional<user> optional= u.findByUsername(principal.getName());
        return optional.get();
    }

    public user unfollow (int userid, Principal principal){
        user user=u.findByUserId(userid);
        String name=principal.getName();
        int  principalid = u.findByUsername(name).get().getUserId();
        user currentuser=u.findByUserId(principalid);
        ArrayList<Integer> arr=user.getFollowers();
        arr.remove(Integer.valueOf(principalid));
        user.setFollowers(arr);
        ArrayList<Integer> arr2=currentuser.getFollowing();
        arr2.remove(Integer.valueOf(userid));
        currentuser.setFollowing(arr2);
        u.save(user);
        u.save(currentuser);
        Optional<user> optional= u.findByUsername(principal.getName());
        return optional.get();
    }

    public List<blog> getblogsoffollowing(Principal principal){
        String name=principal.getName();
        int  principalid = u.findByUsername(name).get().getUserId();
        user currentuser=u.findByUserId(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        ArrayList<blog> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            user user=u.findByUserId(arr.get(i));
            List<blog> blog=BlogRepository.findByUser(user);
            list.addAll(blog);
        }
        return list;
    }
    public List<user> getfollowers(Principal principal){
        String name=principal.getName();
        int  principalid = u.findByUsername(name).get().getUserId();
        user currentuser=u.findByUserId(principalid);
        ArrayList<Integer> arr=currentuser.getFollowers();
        List<user> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            user user=u.findByUserId(arr.get(i));
            list.add(user);
        }
        return list;
    }

    public List<user> getfollowing(Principal principal){
        String name=principal.getName();
        int  principalid = u.findByUsername(name).get().getUserId();
        user currentuser=u.findByUserId(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        List<user> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            user user=u.findByUserId(arr.get(i));
            list.add(user);
        }
        return list;
    }

}