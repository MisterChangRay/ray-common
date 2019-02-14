package com.ray.common.sso.service.sso.impl;

import com.ray.common.core.BaseResponse;
import com.ray.common.core.enums.DBEnum;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.MapBuilder;
import com.ray.common.core.util.ValidUtil;
import com.ray.common.mybatis.MyBatisDao;
import com.ray.common.sso.service.sso.ConstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (ValidUtil.isEmpty(param.get("name")) || ValidUtil.isEmpty(param.get("pid")) || ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[name, pid, id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("name", param.get("name"))
                .add("shortcut", param.get("shortcut"))
                .add("pid", param.get("pid"))
                .add("enabled", DBEnum.TRUE.getCode())
                .add("deleted", DBEnum.FALSE.getCode());

        int i = myBatisDao.insert("const.insert", data);
        if (1 == i) {
            //添加成功后更新上级节点 has_child 状态
            Map updateParent = MapBuilder.build().add("id", data.get("pid")).add("has_child", DBEnum.TRUE.getCode());
            update(updateParent);

            return BaseResponse.build(ResponseEnum.SUCCESS);
        }
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse delete(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }

        //1.检查上级节点子节点个数;调整has_child属性状态
        Object res = myBatisDao.queryOne("const.query", MapBuilder.build().add("id", param.get("id")));
        if (null != res) {
            Map resMap = (Map) res;

            List r = myBatisDao.queryMulti("const.query", MapBuilder.build().add("matchById", resMap.get("pid")));
            if (1 == r.size()) {
                Map updateParent = MapBuilder.build().add("id", resMap.get("pid")).add("has_child", DBEnum.FALSE.getCode());
                update(updateParent);
            }
        }

        //2.删除当前节点
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("deleted", DBEnum.TRUE.getCode());
        int i = myBatisDao.update("const.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);

        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse update(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("name", param.get("name"))
                .add("has_child", param.get("has_child"))
                .add("shortcut", param.get("shortcut"))
                .add("pid", param.get("pid"))
                .add("deleted", param.get("deleted"));

        int i = myBatisDao.update("const.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }

    @Override
    public BaseResponse queryWithPage(Map<String, String> param) {
        if (null == param) param = MapBuilder.build();
        if (ValidUtil.isEmpty(param.get("page"))) param.put("page", "1");
        if (ValidUtil.isEmpty(param.get("pageSize"))) param.put("pageSize", "20");

        param.put("deleted", DBEnum.FALSE.getCode().toString());
        return myBatisDao.queryWithPage("const.query", param);
    }

    @Override
    public BaseResponse query(Map<String, String> param) {
        if (null == param) param = MapBuilder.build();
        if (ValidUtil.isEmpty(param.get("page"))) param.put("page", "1");
        if (ValidUtil.isEmpty(param.get("pageSize"))) param.put("pageSize", "20");

        param.put("deleted", DBEnum.FALSE.getCode().toString());
        List<Map> res = myBatisDao.queryMulti("const.query", param);
        return BaseResponse.build(ResponseEnum.SUCCESS).setData(res);
    }

    @Override
    public BaseResponse enable(Map<String, String> param) {
        if (ValidUtil.isEmpty(param.get("id"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).setMsg("以下参数必传[id]");
        }
        Map data = MapBuilder.build()
                .add("id", param.get("id"))
                .add("enabled", DBEnum.TRUE.getCode());
        int i = myBatisDao.update("const.update", data);
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
        int i = myBatisDao.update("const.update", data);
        if (1 == i) return BaseResponse.build(ResponseEnum.SUCCESS);
        return BaseResponse.build(ResponseEnum.FAILURE);
    }
}
