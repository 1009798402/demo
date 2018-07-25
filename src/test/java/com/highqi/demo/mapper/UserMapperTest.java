package com.highqi.demo.mapper;

import com.highqi.demo.doman.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述:
 *
 * @author C
 * Date: 2018-07-23
 * Time: 21:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;
    @Test
    public void insert() {
        User user = new User();
        user.setUserid("1");
        user.setOpenid("123");
        user.setNickname("怂恿");
        user.setSex("男");
        user.setHobby("爬山");
        user.setPhone("110");
        int insert = mapper.insert(user);
        Assert.assertEquals(1,insert);
    }
    @Test
    public void update(){

        String userid = "1532414506318749551";
        Integer integer = mapper.updateForumheadimgurl("file:D:/test/ttt.jpg", userid);
        System.out.println(integer);

    }

}