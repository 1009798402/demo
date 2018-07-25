package com.highqi.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "projecturl")
public class ProjectUrlConfig {

    /** 微信公众平台授权url. */
    public String wechatMpAuthorize;

    /** 微信拉取用户信息url前缀. */
    public String pullUserMsgPrefix;

}
