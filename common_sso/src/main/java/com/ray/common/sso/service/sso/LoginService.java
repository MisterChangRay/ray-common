package com.ray.common.sso.service.sso;

import com.ray.common.core.BaseResponse;

import java.util.Map;

/**
 * 提供登录服务
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface LoginService {
    BaseResponse login(Map<String, String> param);

    BaseResponse logout(Map<String, String> param);
}
