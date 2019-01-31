package com.ray.common.sso.service.sso.impl;

import com.ray.common.core.BaseResponse;
import com.ray.common.core.enums.DBEnum;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.MapBuilder;
import com.ray.common.core.util.ValidUtil;
import com.ray.common.mybatis.MyBatisDao;
import com.ray.common.sso.dao.entity.User;
import com.ray.common.sso.service.sso.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    MyBatisDao myBatisDao;

    @Override
    public BaseResponse<List> query(Map<String, String> param) {
        return myBatisDao.queryWithPage("role.query", param);
    }

    @Override
    public BaseResponse enable(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("enabled", DBEnum.TRUE.getCode());
        int i = myBatisDao.update("role.update", data);
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
        int i = myBatisDao.update("role.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse add(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("name"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[name]");
        }
        MapBuilder data = MapBuilder.build()
                .add("deleted", DBEnum.FALSE.getCode())
                .add("name", param.get("name"));
        BaseResponse<List> baseResponse = this.query(data);
        if(null != baseResponse.getData()) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("角色已经存在");
        }

        data.add("enabled", DBEnum.TRUE.getCode());

        int i = myBatisDao.insert("role.insert", data);
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
        int i = myBatisDao.update("role.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse update(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        if(ValidUtil.isEmpty(param.get("name"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("没有发现修改内容[name]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("name", param.get("name"));
        int i = myBatisDao.update("role.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }
}
