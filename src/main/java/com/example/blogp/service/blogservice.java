package com.example.blogp.service;




import com.example.blogp.modal.blog;
import com.example.blogp.modal.user;
import com.example.blogp.repository.blogrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class blogservice {
    @Autowired
    private blogrepo BlogRepository;


    @Autowired
    private com.example.blogp.repository.userRepository userRepository;
@Autowired
private blogservice blogservice;
@Autowired
private  currentuser currentUser;

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
    public List<blog> getblogsoffollowing(Principal principal){
        String name=principal.getName();
        int  principalid = userRepository.findByUsername(name).get().getUserId();
        user currentuser=userRepository.findByUserId(principalid);
        ArrayList<Integer> arr=currentuser.getFollowing();
        ArrayList<blog> list=new ArrayList<>();
        for(int i=0;i<arr.size();i++){
            user user=userRepository.findByUserId(arr.get(i));
            List<blog> blog=BlogRepository.findByUser(user);
            list.addAll(blog);
        }
        List<blog> publicblogs= blogservice.getpublicBlogs();
        list.addAll(publicblogs);
        return list;

    }





    public List<blog> getpublicBlogs(){
        return BlogRepository.findByStatus("public");

    }


    public blog update(blog b){
       int blogid= b.getBlogid();
        blog blog=BlogRepository.findByBlogid(blogid);
        System.out.println(blog);
        blog.setBody(b.getBody());
        blog.setTitle(b.getTitle());
        blog.setCreateDate(b.getCreateDate());
        blog.setStatus(b.getStatus());
        return BlogRepository.save(blog);
    }
    public List<blog> deleteblog(int userid, int blogid)
    {
        blog blog1=BlogRepository.findByBlogid(blogid);
        user users=userRepository.findByUserId(userid);
        BlogRepository.delete(blog1);
        return BlogRepository.findByUser(users);

    }
    public List<blog> searchResult(String keyword, Principal principal) {
        List<blog> itemsList = currentUser.getblogsoffollowing(principal);
        List<blog> foundList = new ArrayList<>();

        for (blog items : itemsList) {
            if (items.getTitle() != null && items.getBody() != null && (
                    items.getTitle().toLowerCase().contains(keyword.toLowerCase())
                            || items.getBody().toLowerCase().contains(keyword.toLowerCase())
//                    || items.getCreateDate().getDate().contains(keyword)

            )
            ) {
                foundList.add(items);
            }
        }
        return foundList;
    }
}
