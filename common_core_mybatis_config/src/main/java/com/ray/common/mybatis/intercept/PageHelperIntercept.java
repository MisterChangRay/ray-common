package com.ray.common.mybatis.intercept;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;

/**
 * 文件名：AlipayController.java
 * 版权：Copyright by www.rsrtech.net
 * 修改人：Zhang.Rui
 * 修改时间：2019/1/24
 * 描述：
 * 通过拦截器统一实现分页查询;
 * queryWithPage 方法实现分页数据查询
 * type 拦截的类型 四大对象之一( Executor,ResultSetHandler,ParameterHandler,StatementHandler)
 * method 拦截的方法
 * args 参数
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageHelperIntercept implements Interceptor {

    //每页条目数
    private int pageSize;
    //当前页数
    private int page;
    //数据库类型;mysql
    private String dbType;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //获取StatementHandler，默认是RoutingStatementHandler
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //获取statementHandler包装类
        MetaObject MetaObjectHandler = SystemMetaObject.forObject(statementHandler);

        //分离代理对象链
        while (MetaObjectHandler.hasGetter("h")) {
            Object obj = MetaObjectHandler.getValue("h");
            MetaObjectHandler = SystemMetaObject.forObject(obj);
        }

        while (MetaObjectHandler.hasGetter("target")) {
            Object obj = MetaObjectHandler.getValue("target");
            MetaObjectHandler = SystemMetaObject.forObject(obj);
        }

        //获取进行数据库操作时管理参数的handler
        ParameterHandler parameterHandler = (ParameterHandler) MetaObjectHandler.getValue("delegate.parameterHandler");
        //获取请求时的参数
        Map<String, Object> paraObject = (Map<String, Object>) parameterHandler.getParameterObject();

        //拦截以参数传入withPage=true的请求;统一实现分页功能
        if (null != paraObject.get("withPage") && "true".equals(paraObject.get("withPage"))) {
            //参数名称和在service中设置到map中的名称一致
            page = Integer.valueOf(paraObject.get("page").toString());
            pageSize = Integer.valueOf(paraObject.get("pageSize").toString());

            String sql = statementHandler.getBoundSql().getSql();

            //构建分页功能的sql语句
            String limitSql;
            sql = sql.trim();
            limitSql = MessageFormat.format("{0} LIMIT {1}, {2} ", sql, String.valueOf((page - 1) * pageSize), String.valueOf(pageSize));

            //将构建完成的分页sql语句赋值个体'delegate.boundSql.sql'，偷天换日
            MetaObjectHandler.setValue("delegate.boundSql.sql", limitSql);
        }
        //调用原对象的方法，进入责任链的下一级
        return invocation.proceed();
    }


    //获取代理对象
    @Override
    public Object plugin(Object o) {
        //生成object对象的动态代理对象
        return Plugin.wrap(o, this);
    }

    //设置代理对象的参数
    @Override
    public void setProperties(Properties properties) {
        //如果项目中分页的pageSize是统一的，也可以在这里统一配置和获取，
        //这样就不用每次请求都传递pageSize参数了。参数是在配置拦截器时配置的。
        String limit = properties.getProperty("limit", "10");
        this.pageSize = Integer.valueOf(limit);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
