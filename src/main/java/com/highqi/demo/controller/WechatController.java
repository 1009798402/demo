package com.highqi.demo.controller;


import com.google.gson.Gson;
import com.highqi.demo.config.ProjectUrlConfig;
import com.highqi.demo.doman.User;
import com.highqi.demo.doman.Wechat_UserInfo;
import com.highqi.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 描述:微信相关的Controller
 *
 * @author C
 * Date: 2018-07-25
 * Time: 21:14
 */

@Slf4j
@Controller
@RequestMapping(value = "/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    private final ResourceLoader resourceLoader;
    public WechatController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     * 访问授权的方法
     * @return redirect
     */
    @GetMapping("/authorize")
    public String authorize() {

        //授权url
        String url = projectUrlConfig.getWechatMpAuthorize() + "/wechat/userInfo";

        //回调url  SCOPE_USER_INFO
        //String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, null);
        //SCOPE_BASE
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, null);

        return "redirect:" + redirectUrl;
    }


    /**
     * 用户授权后 回显用户信息页面
     * @param code 授权后的code
     * @return 页面回显
     */
    @GetMapping(value = "/userInfo")
    public String userInfo(@RequestParam("code") String code,
                                 HttpServletRequest request) {

        //sdk
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (Exception e) {
            log.error("【回显用户信息页面 oauth2getAccessToken()方法】{}", e);
            e.printStackTrace();
        }

        //获得accessToken  获得openId
        //拼接 拉取用户信息的url
        String url = projectUrlConfig.pullUserMsgPrefix + wxMpOAuth2AccessToken.getAccessToken() +
                "&openid=" + wxMpOAuth2AccessToken.getOpenId() + "&lang=zh_CN";

        //访问上面的url   获取用户信息json格式
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url, String.class);

        //get请求解决乱码
        try {
            json = new String(json.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("【回显用户信息页面 get请求解决乱码】{}", e);
            e.printStackTrace();
        }

        //转成Wechat_UserInfo对象
        Gson gson = new Gson();
        Wechat_UserInfo userInfo = gson.fromJson(json, Wechat_UserInfo.class);

        //调用方法 获得一个user对象
        User userForm = userService.UserInfo(userInfo);

        //存入request域中  转发到显示的方法
        request.setAttribute("userForm",userForm);
        return "forward:/user/showUser";
    }

    /**
     * 用户保存的方法
     * @param user 表单提交的user对象
     * @return 回显页面
     */
    @PostMapping(value = "/update")
    public String update(User user,
                         HttpServletRequest request) {

        User userForm = userService.update(user);
        request.setAttribute("userForm", userForm);

        //存入request域中  转发到显示的方法
        return "forward:/user/showUser";
    }

}
