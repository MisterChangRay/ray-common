package com.ray.common.sso.controller.sso;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.sso.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 文件名：PermissionController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/17
 * 单点系统-权限管理
 * 描述：
 */
@Controller
@RequestMapping("/sso/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/list")
    @ResponseBody
    public BaseResponse list(@RequestParam Map<String, String> param) {
        return permissionService.query(param);
    }


    @RequestMapping("/enable")
    @ResponseBody
    public BaseResponse enable(@RequestParam Map<String, String> param) {
        return permissionService.enable(param);
    }

    @RequestMapping("/disable")
    @ResponseBody
    public BaseResponse disable(@RequestParam Map<String, String> param) {
        return permissionService.disable(param);
    }

    @RequestMapping("/add")
    @ResponseBody
    public BaseResponse add(@RequestParam Map<String, String> param) {
        return permissionService.add(param);
    }

    @RequestMapping("/update")
    @ResponseBody
    public BaseResponse update(@RequestParam Map<String, String> param) {
        return permissionService.update(param);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestParam Map<String, String> param) {
        return permissionService.delete(param);
    }

}
