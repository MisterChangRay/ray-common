package com.ray.common.sso.service.sso;

import com.ray.common.core.BaseResponse;
import com.ray.common.sso.dao.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 提供用户配置服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface UserService {
    BaseResponse<List<User>> query(Map<String, String> param);

    BaseResponse enable(Map<String, String> param);

    BaseResponse disable(Map<String, String> param);

    BaseResponse add(Map<String, String> param);

    BaseResponse delete(Map<String, String> param);

    BaseResponse update(Map<String, String> param);
}
