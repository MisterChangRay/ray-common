package com.ray.common.sso.service.sso;

import com.ray.common.core.BaseResponse;

import java.util.Map;

/**
 * 提供角色配置服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface RoleService {
    BaseResponse add(Map<String, String> param);

    BaseResponse delete(Map<String, String> param);

    BaseResponse edit(Map<String, String> param);
}