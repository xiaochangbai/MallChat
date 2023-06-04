package com.abin.mallchat.common.user.login.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiaochangbai
 * @date 2023-06-03 10:35
 */
@Data
@Component
@ConfigurationProperties(prefix = OtherOAuth2LoginProperties.PRE)
public class OtherOAuth2LoginProperties {

    public final static String PRE = "other.autho2";


    private String clientId;

    private String clientSecret;
    private String redirectUri;
}
