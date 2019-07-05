package com.bitcoinhelper.notify;

/**
 * 文件名：User.java
 * 修改人：Zhang.Rui
 * 修改时间：2019/7/4
 * 描述：
 */
public class User {
    public String phone;
    public String qq;
    public String email;
    public String type;  //basic,vip


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
