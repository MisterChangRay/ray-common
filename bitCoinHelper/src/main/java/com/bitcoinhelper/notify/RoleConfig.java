package com.bitcoinhelper.notify;

/**
 * 文件名：RoleConfig.java
 * 修改人：Zhang.Rui
 * 修改时间：2019/7/4
 * 描述：
 */
public class RoleConfig {
    public String email;
    public String coin;
    public String phone;
    public String cycle; //周期;单位分钟;
    public String target; //目标差价,达到此差价则通知


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
