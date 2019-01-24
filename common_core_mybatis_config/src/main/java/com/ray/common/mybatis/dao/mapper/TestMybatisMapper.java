package com.ray.common.mybatis.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/24
 * 描述：
 */
@Mapper
public interface TestMybatisMapper {
    @Select("select 1")
    List<Object> query1();
}
