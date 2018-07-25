package com.highqi.demo.service;

import com.highqi.demo.doman.User;
import com.highqi.demo.doman.Wechat_UserInfo;

public interface UserService {

    Integer add(User user);

    User update(User user);

    User UserInfo(Wechat_UserInfo userInfo);
}
