package com.ray.common.sso.service;

import com.ray.common.core.BaseResponse;

import java.util.Map;

/**
 * 登录中心服务
 * 提供登录相关服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface LoginService {
    BaseResponse login(Map<String, String> param);
    BaseResponse logout(Map<String, String> param);

    BaseResponse isLogin(Map<String, String> param);
}
