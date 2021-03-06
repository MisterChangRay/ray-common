package com.ray.common.sso.controller.sso;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.sso.ConstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 文件名：ConstantController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/17
 * 配置中心
 * 描述：
 * 全局常量配置表;
 * 此表再系统初始化后会初始化到redis中
 */
@Controller
@RequestMapping("/sso/constant")
public class ConstantController {
    @Autowired
    ConstService constService;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse add(@RequestParam Map<String, String> param) {
        return constService.add(param);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse update(@RequestParam Map<String, String> param) {
        return constService.update(param);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestParam Map<String, String> param) {
        return constService.delete(param);
    }

    @RequestMapping("/query")
    @ResponseBody
    public BaseResponse query(@RequestParam Map<String, String> param) {
        return constService.queryWithPage(param);
    }


    @RequestMapping("/enable")
    @ResponseBody
    public BaseResponse enable(@RequestParam Map<String, String> param) {
        return constService.enable(param);
    }

    @RequestMapping("/disable")
    @ResponseBody
    public BaseResponse disable(@RequestParam Map<String, String> param) {
        return constService.disable(param);
    }
}
