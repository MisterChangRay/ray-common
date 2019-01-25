package com.ray.common.sso.service.session;

import com.ray.common.core.BaseResponse;

import java.util.Map;

/**
 * 登录中心服务
 * 提供登录相关服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface SessionService {
    BaseResponse createSession(Map<String, String> param);

    BaseResponse destroySession(Map<String, String> param);

    BaseResponse isValidSession(Map<String, String> param);
}
