package com.ray.common.mybatis.common.redis;


import com.ray.common.mybatis.common.redis.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.MessageFormat;

/**
 * 通用结果集
 * 用于ajax返回
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
@ApiModel(description = "标准返回封装-ResultSet")
public class BaseResponse<T> {
    /**
     * 返回消息
     */
    @ApiModelProperty(value = "消息信息", dataType = "String")
    private String msg;
    /**
     * 结果代码,参见 ResultEnum
     */
    @ApiModelProperty(value = "结果代码;0为成功;非0失败", dataType = "Integer")
    private Integer code;
    /**
     * 返回的数据,这里一般是函数的返回值
     */
    @ApiModelProperty(value = "结果返回 JSON 格式", dataType = "JSON")
    private T data;
    /**
     * 页码信息
     */
    @ApiModelProperty(value = "页码信息", dataType = "PageInfo")
    private PageInfo pageInfo;



    public PageInfo getPageInfo() {
        return pageInfo;
    }


    public BaseResponse setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public BaseResponse appendMsg(String msg) {
        this.msg += MessageFormat.format(" [{0}]", msg);
        return this;
    }

    public BaseResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public BaseResponse setCode(ResponseEnum resultEnum) {
        this.code = resultEnum.getCode();
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public BaseResponse setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }


    public static BaseResponse build() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ResponseEnum.SUCCESS.getCode());
        baseResponse.setMsg(ResponseEnum.SUCCESS.getMsg());
        return baseResponse;
    }

    public static BaseResponse build(ResponseEnum resultEnum) {
        BaseResponse baseResponse = new BaseResponse();
        if(null != resultEnum) {
            baseResponse.setCode(resultEnum.getCode());
            baseResponse.setMsg(resultEnum.getMsg());
        } else {
            baseResponse.setCode(ResponseEnum.SERVER_ERROR.getCode());
            baseResponse.setMsg(ResponseEnum.SERVER_ERROR.getMsg());
        }
        return baseResponse;
    }


    private BaseResponse() {}
}


