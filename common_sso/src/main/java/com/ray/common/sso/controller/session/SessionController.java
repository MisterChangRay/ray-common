package com.ray.common.sso.controller.session;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.SessionService;
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
 * 用户登陆
 */
@Component
@RequestMapping("/sso/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse createSession(@RequestParam Map<String, String> param) {
        return sessionService.createSession(param);
    }

    @RequestMapping("/destroy")
    @ResponseBody
    public BaseResponse destroySession(@RequestParam Map<String, String> param) {
        return sessionService.destroySession(param);
    }

    @RequestMapping("/valid")
    @ResponseBody
    public BaseResponse validSession(@RequestParam Map<String, String> param) {
        return sessionService.isValidSession(param);
    }
}
