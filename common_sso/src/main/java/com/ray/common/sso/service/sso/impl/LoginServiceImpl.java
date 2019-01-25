package com.ray.common.sso.service.sso.impl;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.service.session.SessionService;
import com.ray.common.sso.service.sso.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 登录中心服务
 * 提供登录相关服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
@Service
public class LoginServiceImpl  implements LoginService {
    @Autowired
    SessionService sessionService;

    @Override
    public BaseResponse login(Map<String, String> param) {
        return sessionService.createSession(param);
    }

    @Override
    public BaseResponse logout(Map<String, String> param) {
        return sessionService.destroySession(param);
    }
}
