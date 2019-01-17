package com.ray.common.sso.service.impl;

import com.ray.common.sso.service.LoginService;
import org.springframework.stereotype.Component;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/17
 * 描述：
 */
@Component
public class LoginServiceImpl implements LoginService {
    @Override
    public String login(String account, String password) {
        return null;
    }

    @Override
    public String logout(String account, String password) {
        return null;
    }

    @Override
    public boolean isLogin(String session) {
        return false;
    }
}
