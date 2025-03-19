package com.cpystu.houserent.domain;

public class Regist {
    //注册账号密码实体类
    private String account;
    private String psw;

    public Regist(String account, String psw) {//有参构造器
        this.account = account;
        this.psw = psw;
    }

    /**
     * 注册
     */
    public Regist() {//无参
    }

    public String getAccount() {
        return account;
    }

    public String setAccount(String account) {

        this.account = account;
        return account;
    }

    public String getPsw() {
        return psw;
    }

    public String setPsw(String psw) {
        this.psw = psw;
        return psw;
    }
}
