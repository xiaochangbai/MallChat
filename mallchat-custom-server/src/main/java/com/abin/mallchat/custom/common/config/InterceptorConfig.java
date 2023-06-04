package com.abin.mallchat.custom.common.config;

import com.abin.mallchat.custom.common.intecepter.BlackInterceptor;
import com.abin.mallchat.custom.common.intecepter.CollectorInterceptor;
import com.abin.mallchat.custom.common.intecepter.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 配置所有拦截器
 * Author: <a href="https://github.com/zongzibinbin">abin</a>
 * Date: 2023-04-05
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Autowired
    private CollectorInterceptor collectorInterceptor;
    @Autowired
    private BlackInterceptor blackInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/capi/connect/**")
                .addPathPatterns("/capi/**");
        registry.addInterceptor(collectorInterceptor)
                .excludePathPatterns("/capi/connect/**")
                .addPathPatterns("/capi/**");
        registry.addInterceptor(blackInterceptor)
                .excludePathPatterns("/capi/connect/**")
                .addPathPatterns("/capi/**");
    }
}
