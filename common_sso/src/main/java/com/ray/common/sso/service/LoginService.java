package com.ray.common.sso.service;

/**
 * 登录中心服务
 * 提供登录相关服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface LoginService {
    String login(String account, String password);
    String logout(String account, String password);

    boolean isLogin(String session);
}
