package com.abin.mallchat.custom.user.controller;


import com.abin.mallchat.common.common.domain.vo.response.ApiResult;
import com.abin.mallchat.common.user.login.utils.OauthLoginUtils;
import com.abin.mallchat.custom.user.service.WebSocketService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.AuthRequestBuilder;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController
@Api(tags = "联合登录")
@RequestMapping("/capi/connect")
public class ConnectLoginController {


    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private OauthLoginUtils oauthLoginUtils;


    @GetMapping("/login/web/{type}")
    @ApiOperation(value = "WEB信任登录授权,包含PC、WAP")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "登录方式:QQ,微信,微信_PC",
                    allowableValues = "QQ,WECHAT,WECHAT_PC", paramType = "path")
    })
    public ApiResult<String> webAuthorize(@PathVariable String type, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = oauthLoginUtils.buildGiteeAuthRequest();
        String uuId = UUID.randomUUID().toString().replaceAll("-","");
        log.info("生成uuid：{}",uuId);
        String url = authRequest.authorize(uuId);
        return ApiResult.success(url);
    }


    @ApiOperation(value = "信任登录统一回调地址", hidden = true)
    @GetMapping("/callback/{type}")
    public String callBack(@PathVariable String type, AuthCallback callback, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        AuthRequest authRequest = oauthLoginUtils.buildGiteeAuthRequest();
        AuthResponse response = authRequest.login(callback);
        int code = response.getCode();
        if(code==2000){
            String state = callback.getState();
            AuthUser data = (AuthUser) response.getData();
            webSocketService.giteeConfireLogin(state,data);
            return "success";
        }
        log.info("gitee响应1：{}",callback);
        return "fail";
    }




}
