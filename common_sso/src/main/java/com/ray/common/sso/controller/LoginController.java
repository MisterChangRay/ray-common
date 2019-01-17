package com.ray.common.sso.controller;

import com.ray.common.core.BaseResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/17
 * 描述：
 */
@Component
@RequestMapping("/session")
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(String account, String password) {
        return BaseResponse.build();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public BaseResponse logout(String account, String password) {
        return BaseResponse.build();
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public BaseResponse isLogin(String session) {
        return BaseResponse.build();
    }
}
