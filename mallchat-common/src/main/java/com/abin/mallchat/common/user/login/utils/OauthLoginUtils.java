package com.abin.mallchat.common.user.login.utils;

import com.abin.mallchat.common.user.login.config.OtherOAuth2LoginProperties;
import me.zhyd.oauth.AuthRequestBuilder;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiaochangbai
 * @date 2023-06-03 10:43
 */
@Component
public class OauthLoginUtils {

    @Autowired
    private OtherOAuth2LoginProperties otherOAuth2LoginProperties;


    public AuthRequest buildGiteeAuthRequest(){
        return AuthRequestBuilder.builder()
                .source("gitee")
                .authConfig((source) -> {
                    // 通过 source 动态获取 AuthConfig
                    // 此处可以灵活的从 sql 中取配置也可以从配置文件中取配置
                    return AuthConfig.builder()
                            .clientId(otherOAuth2LoginProperties.getClientId())
                            .clientSecret(otherOAuth2LoginProperties.getClientSecret())
                            .redirectUri(otherOAuth2LoginProperties.getRedirectUri())
                            .build();
                })
                .build();
    }
}
