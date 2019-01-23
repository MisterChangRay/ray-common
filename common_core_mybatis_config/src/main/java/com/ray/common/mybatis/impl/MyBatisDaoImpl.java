package com.ray.common.mybatis.impl;

import com.ray.common.mybatis.MyBatisDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public T queryOne(String key, Map<String, String> param) {
        return sqlSessionTemplate.selectOne(key, param);
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
