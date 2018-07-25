package com.highqi.demo.doman;


import lombok.Data;

/**
 * 描述:存入数据库  或者  与前端页面交互的user对象
 *
 * @author C
 * Date: 2018-07-23
 * Time: 20:20
 */
@Data
public class User {

    /** 用户id. */
    private String userid;
    /** 头像. */
    private String headimgurl;
    /** openid. */
    private String openid;
    /** 性别. */
    private String sex;
    /** 昵称. */
    private String nickname;
    /** 电话号码. */
    private String phone;
    /** 个人兴趣. */
    private String hobby;
    /** 论坛头像. */
    private String forumheadimgurl;

}