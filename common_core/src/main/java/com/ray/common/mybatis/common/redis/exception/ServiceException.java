package com.ray.common.mybatis.common.redis.exception;

import com.ray.common.mybatis.common.redis.enums.ResponseEnum;

/**
 * @author Created by rui.zhang on 2018/6/4.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description
 * 封装全局统一异常返回；
 * 此类作为业务异常返回封装
 * 你可以在代码中任何地方返回错误信息到前端
 */
public class ServiceException extends Exception {
    private String msg;
    private ResponseEnum resultEnum;

    public ServiceException(ResponseEnum resultEnum, String errorMsg) {
        super();
        this.resultEnum = resultEnum;
        this.msg = resultEnum.getMsg();

        if(null != errorMsg) {
            this.msg = errorMsg;
        }
    }

    public ServiceException(ResponseEnum resultEnum) {
        super();
        this.resultEnum = resultEnum;
        this.msg = resultEnum.getMsg();

    }

    public ServiceException(String errorMsg) {
        super();
        this.msg = errorMsg;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResponseEnum resultEnum) {
        this.resultEnum = resultEnum;
    }


    @Override
    public String toString() {
        return "ServiceException{" +
                "msg='" + msg + '\'' +
                ", resultEnum=" + resultEnum +
                '}';
    }
}