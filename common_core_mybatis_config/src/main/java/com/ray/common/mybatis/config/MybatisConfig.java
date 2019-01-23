package com.ray.common.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/23
 * 描述：
 */
@Configuration
@PropertySource({"classpath:jdbc.properties", "classpath:mybatis.properties"})
public class MybatisConfig {
}
