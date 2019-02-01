package com.ray.common.sso.service.sso.impl;

import com.ray.common.core.BaseResponse;
import com.ray.common.core.enums.DBEnum;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.MapBuilder;
import com.ray.common.core.util.ValidUtil;
import com.ray.common.mybatis.MyBatisDao;
import com.ray.common.sso.dao.entity.User;
import com.ray.common.sso.service.sso.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 提供权限相关服务
 * 这里的权限主要是指资源权限;例如菜单;按钮
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    MyBatisDao myBatisDao;

    @Override
    public BaseResponse<List<User>> query(Map<String, String> param) {
        return myBatisDao.queryWithPage("permission.query", param);
    }

    @Override
    public BaseResponse enable(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("enabled", DBEnum.TRUE.getCode());
        int i = myBatisDao.update("permission.update", data);
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
        int i = myBatisDao.update("permission.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse add(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("name")) || ValidUtil.isEmpty(param.get("uri"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[name, uri, puri]");
        }
        MapBuilder data = MapBuilder.build()
                .add("uri", param.get("uri"))
                .add("deleted", DBEnum.FALSE.getCode());
        BaseResponse<List> baseResponse = this.query(data);
        if(null != baseResponse.getData()) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("权限已经存在");
        }

        data.add("name", param.get("name"))
            .add("uri", param.get("uri"))
            .add("type", param.get("type"))
            .add("puri", param.get("puri"))
            .add("enabled", DBEnum.TRUE.getCode())
            .add("deleted", DBEnum.FALSE.getCode());

        int i = myBatisDao.insert("permission.insert", data);
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
        int i = myBatisDao.update("permission.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse update(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        if(ValidUtil.isEmpty(param.get("name")) && ValidUtil.isEmpty(param.get("type"))&&
                ValidUtil.isEmpty(param.get("uri")) && ValidUtil.isEmpty(param.get("puri"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("没有发现修改内容[name | type | uri | puri]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("name", param.get("name"))
                .add("type", param.get("type"))
                .add("uri", param.get("uri"))
                .add("puri", param.get("puri"));
        int i = myBatisDao.update("permission.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }
}
