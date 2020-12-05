package com.migu.tntsb.tntsb;

import com.migu.tntsb.tntsb.domain.Post;
import com.migu.tntsb.tntsb.domain.User;
import com.migu.tntsb.tntsb.mailService.mailSender;
import com.migu.tntsb.tntsb.mapper.postMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class TntsbApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private postMapper postMapper;
   /* @Test
    void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
            int id = resultSet.getInt(3);
            String name = resultSet.getString(1);
            String address = resultSet.getString(2);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(("select * from user"));
        while(resultSet.next()){
            System.out.println("id:" + id + " name:" + name + " address:" + address);
        }
    }*/
   @Test void testUserMapper(){
   //    User userById = postMapper.get(1);
      // System.out.println(userById.toString());
   }
    @Test void testUserJsonlize(){
        Post postById = postMapper.getPostbyId(1);

    }
}
