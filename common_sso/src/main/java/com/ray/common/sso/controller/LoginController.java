package com.ray.common.sso.controller;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

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
    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestParam Map<String, String> param) {
        return loginService.logout(param);
//        return loginService.login(param);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public BaseResponse logout(@RequestParam Map<String, String> param) {
        return BaseResponse.build();
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public BaseResponse isLogin(@RequestParam Map<String, String> param) {
        return BaseResponse.build();
    }
}
