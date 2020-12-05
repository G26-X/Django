package com.migu.tntsb.tntsb.mapper;

import com.migu.tntsb.tntsb.domain.Comment;
import com.migu.tntsb.tntsb.domain.Post;
import com.migu.tntsb.tntsb.domain.User;
import javafx.geometry.Pos;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan("com.migu.tntsb")
public interface postMapper {
 Post getPostbyId(Integer id);
 List<Post> getAllPosts();
 void addPost(String name,String img,String discribe);
 void likePost(Integer pid);
 List<Comment> getComments(Integer pid);
 void postComments(Integer pid,String cmmd,String userName,String userIcon);
}
