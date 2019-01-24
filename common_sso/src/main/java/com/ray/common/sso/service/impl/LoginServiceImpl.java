package com.ray.common.sso.service.impl;

import com.ray.common.core.BaseResponse;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.MapBuilder;
import com.ray.common.core.util.ValidUtil;
import com.ray.common.mybatis.MyBatisDao;
import com.ray.common.redis.RedisService;
import com.ray.common.redis.config.RedisPath;
import com.ray.common.sso.dao.entity.User;
import com.ray.common.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private RedisService redisService;
    @Autowired
    private MyBatisDao myBatisDao;


    //手机号登录
    private BaseResponse<User> loginByPhone(Map<String, String> param) {
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    //账号密码登录
    private BaseResponse<User> loginByUserName(Map<String, String> param) {
        Map<String, String> condition = MapBuilder.build();
        condition.put("username", param.get("username"));
        condition.put("password", param.get("password"));
        List<User> users = myBatisDao.queryMulti("user.query", condition);
        if(null == users || 0 == users.size()) {
            return BaseResponse.build(ResponseEnum.FAILURE).appendMsg("用户不存在或者账号密码错误");
        }
        User u = users.get(0);
        return BaseResponse.build(ResponseEnum.SUCCESS).setData(u);
    }

    @Override
    public BaseResponse login(Map<String, String> param) {
        if((ValidUtil.isEmpty(param.get("phone")) && ValidUtil.isEmpty(param.get("vercode"))) ||
                (ValidUtil.isEmpty(param.get("username")) && ValidUtil.isEmpty(param.get("password")))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("请传入登录参数[(username & password) || (phone & vercode)]");
        }

        BaseResponse<User> res = null;
        if(!ValidUtil.isEmpty("username")) {
            res =  loginByUserName(param);
        } else if(!ValidUtil.isEmpty("phone")) {
            res =  loginByPhone(param);
        }
        if(0 != res.getCode()) return res;

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisService.hset(RedisPath.TOKENS, token, res.getData());
        //登录成功
        return BaseResponse.build(ResponseEnum.SUCCESS);
    }


    @Override
    public BaseResponse logout(Map<String, String> param) {
        return myBatisDao.queryWithPage("user.queryConst", param);
    }

    @Override
    public BaseResponse isLogin(Map<String, String> param) {
        if(ValidUtil.isEmpty(param.get("token"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("请传入参数[token]");
        }
        Object o = redisService.hget(RedisPath.TOKENS, param.get("token"));
        if(null == o) {
            return BaseResponse.build(ResponseEnum.INVALID_TOKEN);
        } else  {
            return BaseResponse.build(ResponseEnum.SUCCESS).setData(o);
        }
    }
}
