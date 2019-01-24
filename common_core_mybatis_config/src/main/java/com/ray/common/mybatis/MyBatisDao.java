package com.ray.common.mybatis;

import com.ray.common.core.BaseResponse;

import java.util.List;
import java.util.Map;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/23
 * 描述：
 */
public interface MyBatisDao<T> {
    T queryOne(String key, Map<String, String> param);

    /**
     * 带分页查询,请传入 page, pageSize 参数
     * 书写的 sql 不要使用 limit 进行操作否则功能不能正常执行
     * @param key
     * @param param
     * @return
     */
    BaseResponse<T> queryWithPage(String key, Map<String, String> param);

    List<T> queryMulti(String key, Map<String, String> param);

    List<T> queryMulti(String key);

    int insert(String key, Map<String, String> param);

    int insert(String key);

    int update(String key, Map<String, String> param);

    int update(String key);

    int delete(String key, Map<String, String> param);

    int delete(String key);

}
