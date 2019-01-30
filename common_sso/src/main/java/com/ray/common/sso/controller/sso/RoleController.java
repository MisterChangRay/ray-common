package com.ray.common.sso.controller.sso;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.session.SessionService;
import com.ray.common.sso.service.sso.RoleService;
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
    RoleService roleService;

    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse list(@RequestParam Map<String, String> param) {
        return roleService.query(param);
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse add(@RequestParam Map<String, String> param) {
        return roleService.add(param);
    }

    @RequestMapping("/update")
    @ResponseBody
    public BaseResponse update(@RequestParam Map<String, String> param) {
        return roleService.update(param);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestParam Map<String, String> param) {
        return roleService.delete(param);
    }


}
