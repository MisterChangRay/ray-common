package com.ray.common.sso.controller.sso;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/17
 * 描述：
 * 单点系统登陆
 */
@Controller
@RequestMapping("/sso")
public class LoginController {
    @Autowired
    SessionService sessionService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse createSession(@RequestParam Map<String, String> param) {
        return sessionService.createSession(param);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse destroySession(@RequestParam Map<String, String> param) {
        return sessionService.destroySession(param);
    }

}
