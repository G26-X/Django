package com.migu.tntsb.tntsb.mapper;

import com.migu.tntsb.tntsb.domain.Post;
import com.migu.tntsb.tntsb.domain.User;

import java.util.List;

public class userMapper {
    List<User> getAllUsers();
    User getUserInfoByuid(Integer id);
}
