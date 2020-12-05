package com.migu.tntsb.tntsb.controller;

import com.migu.tntsb.tntsb.domain.Comment;
import com.migu.tntsb.tntsb.domain.Post;
import com.migu.tntsb.tntsb.domain.Response;
import com.migu.tntsb.tntsb.domain.User;
import com.migu.tntsb.tntsb.mapper.postMapper;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/post")
public class UserController {

    @Autowired
    public UserController(postMapper postMapper){
        this.postMapper = postMapper;
    }
    private postMapper postMapper;
    @GetMapping
    @RequestMapping(value = "/getPost")
    public Response<Post> get(@RequestParam Integer pid){
        Response<Post> response = new Response<>();
        Post postById = postMapper.getPostbyId(pid);
        if(postById==null){
            response.setCode(404);
            response.setMessage("User not Found");
        }
        response.setData(postById);
        return  response;
    }
    @GetMapping
    @RequestMapping(value = "/getPostAll")
    public Response<List<Post>> getPosts(){
        Response<List<Post>> response = new Response<>();
        List<Post> posts = postMapper.getAllPosts();
        if(posts==null){
            response.setCode(404);
            response.setMessage("User not Found");
        }
        response.setData(posts);
        return  response;
    }
    @PostMapping("/upload")
    public Response<String> upload(@RequestParam Map<String,String> map, @RequestParam("file")MultipartFile image) throws IOException{
        Response<String> response = new Response<>();
        response.setData("Success");
        String imgName = UUID.randomUUID().toString().replace("_", "") + "_" + image.getOriginalFilename().replaceAll(" ", "");
//保存图片
        String imgFilePath="./"+imgName;
        OutputStream out = new FileOutputStream(imgFilePath);
        out.write(image.getBytes());
        out.flush();
        out.close();
        String name=map.get("uploader");
        String discricpt=map.get("discricpt");
        postMapper.addPost(name,imgName,discricpt);
        return response;
    }
    @GetMapping
    @RequestMapping(value = "/likepost")
    public Response<String> like(@RequestParam Integer pid){
        Response<String> response = new Response<>();
        postMapper.likePost(pid);
        response.setData("Liked");
        return  response;
    }
    @GetMapping
    @RequestMapping(value = "/getComments")
    public Response<List<Comment>> getComments(@RequestParam Integer pid,@RequestParam String cmmd,@RequestParam String userName,@RequestParam String userIcon){
        @Autowired
        Response<List<Comment>> response = new Response<>();
        List<Comment> postById = postMapper.getComments(pid);
        response.setData(postById);
        return  response;
    }
    @GetMapping
    @RequestMapping(value = "/postComments")
    public void postComments(@RequestParam Integer pid,@RequestParam String cmmd,@RequestParam String userName,@RequestParam String userIcon){
        postMapper.postComments(pid,cmmd,userName,userIcon);
        return  ;
    }
}
