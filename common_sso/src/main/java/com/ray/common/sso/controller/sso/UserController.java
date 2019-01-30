package com.ray.common.sso.controller.sso;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.session.SessionService;
import com.ray.common.sso.service.sso.UserService;
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
@RequestMapping("/sso/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse list(@RequestParam Map<String, String> param) {
        return userService.query(param);
    }


    @RequestMapping("/enable")
    @ResponseBody
    public BaseResponse enable(@RequestParam Map<String, String> param) {
        return userService.enable(param);
    }

    @RequestMapping("/disable")
    @ResponseBody
    public BaseResponse disable(@RequestParam Map<String, String> param) {
        return userService.disable(param);
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse add(@RequestParam Map<String, String> param) {
        return userService.add(param);
    }

    @RequestMapping("/update")
    @ResponseBody
    public BaseResponse update(@RequestParam Map<String, String> param) {
        return userService.update(param);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestParam Map<String, String> param) {
        return userService.delete(param);
    }

}
