package com.ray.common.mybatis.common.sso.service.impl;

import com.ray.common.mybatis.MyBatisDao;
import com.ray.common.mybatis.common.redis.BaseResponse;
import com.ray.common.mybatis.common.redis.RedisService;
import com.ray.common.mybatis.common.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/17
 * 描述：
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    RedisService redisService;
    @Autowired
    MyBatisDao myBatisDao;

    @Override
    public BaseResponse login(Map<String, String> param) {
        redisService.set("aaa", param);
        return null;
    }

    @Override
    public BaseResponse logout(Map<String, String> param) {
        return null;
    }

    @Override
    public BaseResponse isLogin(Map<String, String> param) {
        return null;
    }
}
