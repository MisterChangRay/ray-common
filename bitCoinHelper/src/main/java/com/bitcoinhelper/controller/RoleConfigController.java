package com.bitcoinhelper.controller;

import com.bitcoinhelper.notify.RoleConfig;
import com.bitcoinhelper.notify.User;
import com.ray.common.core.BaseResponse;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.JSONUtil;
import com.ray.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/7/4
 * 描述：
 */
@Controller
@RequestMapping("/bitcoin/helper")
public class RoleConfigController {
    public static String ROLE_CONFIG_KEY = "com:bitcoin:helper:config";
    public static String USER_CONFIG_KEY = "com:bitcoin:helper:user";

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addRole( RoleConfig roleConfig) {

        Object config = redisService.get(ROLE_CONFIG_KEY);
        List<RoleConfig> res = JSONUtil.json2list(String.valueOf(config), RoleConfig.class);
        if(null != res && 0 != res.size()) {
            boolean exist = res.stream().anyMatch(t -> {
                if(t.email.equals(roleConfig.email)) {
                    return true;
                }
                return false;
            });

            if(exist) {
                return BaseResponse.build(ResponseEnum.EXIST).setMsg("不能添加多个策略,多个策略请联系管理员[QQ1070509085]");
            }
        } else {
            res = new ArrayList<>();
        }

        res.add(roleConfig);

        redisService.set(ROLE_CONFIG_KEY, JSONUtil.obj2json(res));
        return BaseResponse.build();
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addUser( User user) {
        Object config = redisService.get(USER_CONFIG_KEY);
        List<User> res = JSONUtil.json2list(String.valueOf(config), User.class);
        if(null != res && 0 != res.size()) {
            boolean exist = res.stream().anyMatch(t -> {
                if(t.phone.equals(user.phone)) {
                    return true;
                }
                return false;
            });
            if(exist) {
                return BaseResponse.build(ResponseEnum.EXIST);


            }
        } else {
            res = new ArrayList<>();
        }

        user.type = null;
        res.add(user);

        redisService.set(USER_CONFIG_KEY, JSONUtil.obj2json(res));
        return BaseResponse.build();
    }
}
