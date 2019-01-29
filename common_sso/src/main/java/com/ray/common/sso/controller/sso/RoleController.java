package com.ray.common.sso.controller.sso;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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
 * 单点系统-角色管理
 */
@Controller
@RequestMapping("/sso/role")
public class RoleController {
    @Autowired
    SessionService sessionService;



    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse add(@RequestParam Map<String, String> param) {
        return sessionService.createSession(param);
    }

    @RequestMapping("/update")
    @ResponseBody
    public BaseResponse update(@RequestParam Map<String, String> param) {
        return sessionService.destroySession(param);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestParam Map<String, String> param) {
        return sessionService.destroySession(param);
    }


}
