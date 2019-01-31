package com.ray.common.mybatis.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.ray.common.core.BaseResponse;
import com.ray.common.core.PageInfo;
import com.ray.common.core.enums.ResponseEnum;
import com.ray.common.core.util.ValidUtil;
import com.ray.common.mybatis.MyBatisDao;
import org.apache.ibatis.mapping.MappedStatement;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/23
 * 描述：
 */
@Service
public class MyBatisDaoImpl<T> implements MyBatisDao<T> {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    DruidDataSource druidDataSource;


    @Override
    public T queryOne(String key, Map<String, String> param) {
        return sqlSessionTemplate.selectOne(key, param);
    }


    @Override
    public BaseResponse<T> queryWithPage(String key, Map<String, String> param) {
        if(ValidUtil.isEmpty(param.get("page")) || ValidUtil.isEmpty(param.get("pageSize"))) {
            return BaseResponse.build(ResponseEnum.FAILURE).appendMsg("分页参数必传[page, pageSize]");
        }
        param.put("withPage", "true");
        MappedStatement baseMappedStatement =  sqlSessionTemplate.getConfiguration().getMappedStatement(key);
        String baseSql = baseMappedStatement.getBoundSql(param).getSql();
        //如果原始语句已经添加过 limit 则不能再再添加limit,自动分页失败
        if(baseSql.matches("limit[\\S\\,d]+?$")) {
            return BaseResponse.build(ResponseEnum.FAILURE).appendMsg("分页查询失败[原始语句中已包含limit]");
        }



        //1.组装查询总记录sql
        String countSql = baseSql.replaceFirst("(?i)SELECT.+?FROM ", "SELECT COUNT(1) COUNT FROM ");
        PreparedStatement preparedStatement = null;
        DruidPooledConnection druidPooledConnection = null;
        PageInfo pageInfo = new PageInfo();
        try {
            druidPooledConnection = druidDataSource.getConnection();
            preparedStatement =  druidPooledConnection.prepareStatement(countSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            pageInfo.setPage(Integer.valueOf(param.get("page")));
            pageInfo.setPageSize(Integer.valueOf(param.get("pageSize")));
            pageInfo.setCount(resultSet.getLong("count"));

            resultSet.close();
            preparedStatement.close();
            druidPooledConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return BaseResponse.build(ResponseEnum.SUCCESS).setData(sqlSessionTemplate.selectList(key , param)).setPageInfo(pageInfo);
    }


    @Override
    public List<T> queryMulti(String key, Map<String, String> param) {
        return sqlSessionTemplate.selectList(key, param);
    }

    @Override
    public List<T> queryMulti(String key) {
        return sqlSessionTemplate.selectList(key);
    }

    @Override
    public int insert(String key, Map<String, String> param) {
        return sqlSessionTemplate.insert(key, param);
    }

    @Override
    public int insert(String key) {
        return sqlSessionTemplate.insert(key);
    }

    @Override
    public int update(String key, Map<String, String> param) {
        return sqlSessionTemplate.update(key, param);
    }

    @Override
    public int update(String key) {
        return sqlSessionTemplate.update(key);
    }

    @Override
    public int delete(String key, Map<String, String> param) {
        return sqlSessionTemplate.delete(key, param);
    }

    @Override
    public int delete(String key) {
        return sqlSessionTemplate.delete(key);
    }

}
