package com.highqi.demo.doman;

import lombok.Data;

/**
 * 描述:用来接收访问微信url后
 *      拉取用户信息的对象
 * @author C
 * Date: 2018-07-23
 * Time: 20:20
 */
@Data
public class Wechat_UserInfo {

    /** openid. */
    private String openid;
    /** 昵称. */
    private String nickname;
    /** 性别. */
    private Integer sex;
    /** 语言. */
    private String language;
    /** 市. */
    private String city;
    /** 省. */
    private String province;
    /** 国家. */
    private String country;
    /** 头像url. */
    private String headimgurl;
    /** 特权. */
    private String[] privilege;

}
