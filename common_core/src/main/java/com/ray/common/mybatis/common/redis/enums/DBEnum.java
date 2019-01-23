package com.ray.common.mybatis.common.redis.enums;

/**
 * 设置数据库中的一些枚举
 * 请注意此枚举为数据库中使用的枚举类型，非页面枚举
 * 页面枚举应放置到 constants 表中
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/27/2018.
 */
public enum DBEnum {
    TRUE(1, "true"), //表肯定
    FALSE(0, "false"), //表否定

    QUERY(100, "query"), //查询
    INSERT(101, "insert"),//新增
    UPDATE(102, "update"),//修改
    DELETE(103, "delete");//删除

    private Integer code;
    private String desc;

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
    DBEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
