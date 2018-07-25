package com.highqi.demo.controller;

import com.highqi.demo.doman.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述:用户相关的Controller
 *
 * @author C
 * Date: 2018-07-25
 * Time: 21:14
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    /** 回显用户信息页面. */
    @RequestMapping(value = "/showUser")
    public ModelAndView showUser(HttpServletRequest request,
                                 Map<String, Object> map){

        //从request域中取出user
        User userForm = (User) request.getAttribute("userForm");

        if (!StringUtils.isEmpty(userForm.getHobby())) {
            //如果爱好不为null  切割成一个list 放入map
            List<String> hobbys = Arrays.asList(userForm.getHobby().split(","));
            //携带参数hobbys
            map.put("hobbys", hobbys);
        }
        //携带参数userForm
        map.put("userForm", userForm);

        //获取论坛头像图片名称
        String forumheadimgurl = userForm.getForumheadimgurl();

        if (!StringUtils.isEmpty(forumheadimgurl)){
            //携带参数名称
            map.put("fileName",forumheadimgurl.trim());
        }
        return new ModelAndView("/demo/login", map);
    }
}
