package com.ray.common.sso.service.sso.impl;

import com.ray.common.core.BaseResponse;
import com.ray.common.core.enums.DBEnum;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.MapBuilder;
import com.ray.common.core.util.ValidUtil;
import com.ray.common.mybatis.MyBatisDao;
import com.ray.common.sso.dao.entity.User;
import com.ray.common.sso.service.sso.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 提供用户相关服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MyBatisDao myBatisDao;

    @Override
    public BaseResponse<List<User>> query(Map<String, String> param) {
        if(null == param) param = MapBuilder.build();
        param.put("deleted", DBEnum.FALSE.getCode().toString());
        return myBatisDao.queryWithPage("user.query", param);
    }

    @Override
    public BaseResponse enable(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("enabled", DBEnum.TRUE.getCode());
        int i = myBatisDao.update("user.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse disable(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("enabled", DBEnum.FALSE.getCode());
        int i = myBatisDao.update("user.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse add(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("username")) || ValidUtil.isEmpty(param.get("password"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[username, password, phone]");
        }
        MapBuilder data = MapBuilder.build()
                .add("username", param.get("username"))
                .add("deleted", DBEnum.FALSE.getCode());
        BaseResponse<List> baseResponse = this.query(data);
        if(null != baseResponse.getData()) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("用户名已经存在");
        }

        data.add("password", param.get("password"))
            .add("name", param.get("name"))
            .add("idcard", param.get("idcard"))
            .add("phone", param.get("phone"))
            .add("email", param.get("email"))
            .add("enabled", DBEnum.TRUE.getCode());

        int i = myBatisDao.insert("user.insert", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse delete(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("deleted", DBEnum.TRUE.getCode());
        int i = myBatisDao.update("user.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse update(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        if(ValidUtil.isEmpty(param.get("password")) && ValidUtil.isEmpty(param.get("phone"))&&
                ValidUtil.isEmpty(param.get("email")) && ValidUtil.isEmpty(param.get("idcard"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("没有发现修改内容[password | phone | email | idcard]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("password", param.get("password"))
                .add("idcard", param.get("idcard"))
                .add("name", param.get("name"))
                .add("phone", param.get("phone"))
                .add("email", param.get("email"));
        int i = myBatisDao.update("user.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }
}
