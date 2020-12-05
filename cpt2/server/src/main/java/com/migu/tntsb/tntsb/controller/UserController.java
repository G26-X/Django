package com.migu.tntsb.tntsb.controller;

import com.migu.tntsb.tntsb.domain.Post;
import com.migu.tntsb.tntsb.domain.Response;
import com.migu.tntsb.tntsb.domain.User;
import com.migu.tntsb.tntsb.mapper.postMapper;
import com.migu.tntsb.tntsb.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    public UserController(com.migu.tntsb.tntsb.mapper.userMapper userMapper){
        this.userMapper = userMapper;
    }
    private userMapper userMapper;
    @GetMapping
    @RequestMapping(value = "/getAllUser")
    public Response<List<User>> getPosts(){
        Response<List<User>> response = new Response<>();
        List<User> users = userMapper.getAllUsers();
        if(users==null){
            response.setCode(404);
            response.setMessage("User not Found");
        }
        response.setData(users);
        return  response;
    }
    @GetMapping
    @RequestMapping(value = "/getPost")
    public Response<User> getUserInfoByuid(@RequestParam Integer pid){
        Response<User> response = new Response<>();
        User userById = userMapper.getUserInfoByuid(pid);
        if(userById==null){
            response.setCode(404);
            response.setMessage("User not Found");
        }
        response.setData(userById);
        return  response;
    }
}
