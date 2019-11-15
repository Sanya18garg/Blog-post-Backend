package com.example.blogp.controller;

import com.example.blogp.modal.comments;
import com.example.blogp.service.blogservice;
import com.example.blogp.service.commentService;
import com.example.blogp.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentContoller {
    @Autowired
    blogservice blogService;
    @Autowired
    userservice userService;
    @Autowired
    com.example.blogp.service.commentService commentService;

    @PostMapping(value="/addcomment/{blogid}")
    @ResponseBody
    public comments addcomments(@Valid @RequestBody comments comment, @PathVariable("blogid")int id, Principal principal)
    {
        return commentService.addcomment(comment,id,userService.getUserId(principal));
    }

    @RequestMapping(value="/showall",method= RequestMethod.GET)
    @ResponseBody
    public List<comments> showblogs()
    {

        return commentService.getList();
    }

    @GetMapping(value="/getbyblog/{blogid}")
    public List<comments> getCommentByBlog(@PathVariable("blogid")int id){
        return commentService.getbyblog(id);
    }

    @RequestMapping(value="/deletecomment/{commentid}",method= RequestMethod.GET)
    @ResponseBody
    public List<comments> deletecomment(@PathVariable("commentid") int commentid)
    {
        return commentService.deletecomment(commentid);
    }
}
