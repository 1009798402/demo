package com.highqi.demo.service.impl;

import com.highqi.demo.doman.User;
import com.highqi.demo.doman.Wechat_UserInfo;
import com.highqi.demo.mapper.UserMapper;
import com.highqi.demo.service.UserService;
import com.highqi.demo.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 描述:用户数据增删改查的service实现类
 *
 * @author C
 * Date: 2018-07-23
 * Time: 21:40
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /** 添加用户. */
    @Override
    public Integer add(User user) {
        return userMapper.insert(user);
    }

    /** 跟新用户. */
    @Override
    public User update(User user) {

        //根据更新的user的Openid查询数据库中原有的user
        User result = userMapper.selectByOpenid(user.getOpenid());

        //设置新的电话 爱好
        result.setPhone(user.getPhone());
        result.setHobby(user.getHobby());

        //跟新数据库
        Integer update = userMapper.updateByPrimaryKey(result);

        //跟新成功返回跟新后的User对象 失败返回null
        if (update > 0){
            return result;
        }
        return null;
    }

    /** 查询用户(回显). */
    @Override
    public User UserInfo(Wechat_UserInfo userInfo) {

        //根据Openid查询对应的User
        User user = userMapper.selectByOpenid(userInfo.getOpenid());

        // 如果 查询到用户  直接返回
        if (user != null){
            return user;
        } else {
            //否则创建一个User 填充字段
            User adduser = new User();
            //生成随机字符串作为Userid
            adduser.setUserid(KeyUtil.genUniqueKey());
            //根据参数设置性别
            Integer sex = userInfo.getSex();
            if (sex == 1 ){
                adduser.setSex("男");
            }else if (sex == 2 ){
                adduser.setSex("女");
            }else {
                adduser.setSex("未设置");
            }
            adduser.setNickname(userInfo.getNickname());
            adduser.setHeadimgurl(userInfo.getHeadimgurl());
            adduser.setOpenid(userInfo.getOpenid());
            //调用添加用户的方法
            Integer num = add(adduser);
            //添加成功返回用户对象  失败返回null
            if (num > 0) {
                return adduser;
            }
            return null;
        }
    }
}
