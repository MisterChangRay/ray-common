package com.ray.common.sso.service.sso.impl;

import com.ray.common.core.BaseResponse;
import com.ray.common.core.enums.DBEnum;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.MapBuilder;
import com.ray.common.core.util.ValidUtil;
import com.ray.common.mybatis.MyBatisDao;
import com.ray.common.sso.service.sso.ConstService;
import com.ray.common.sso.service.sso.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 提供全局配置相关服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
@Service
public class ConstServiceImpl implements ConstService {
    @Autowired
    MyBatisDao myBatisDao;

    @Override
    public BaseResponse add(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("name")) || ValidUtil.isEmpty(param.get("pid"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[name, pid, phone]");
        }
        Map data = MapBuilder.build()
                .add("name", param.get("name"))
                .add("shortcut", param.get("shortcut"))
                .add("pid", param.get("pid"))
                .add("enabled", DBEnum.TRUE)
                .add("deleted", DBEnum.FALSE);
        int i = myBatisDao.insert("const.insert", data);
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
                .add("deleted", DBEnum.TRUE);
        int i = myBatisDao.update("const.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse edit(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("name", param.get("name"))
                .add("shortcut", param.get("shortcut"))
                .add("pid", param.get("pid"))
                .add("enabled", DBEnum.TRUE);
        int i = myBatisDao.update("user.insert", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }
}
