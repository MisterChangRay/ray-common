package com.ray.common.core.util;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/24
 * 描述：
 */
public class ValidUtil {
    public static boolean isEmpty(Object o) {
        return null == o || o.toString().equals("");
    }
}
