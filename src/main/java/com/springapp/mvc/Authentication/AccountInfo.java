package com.springapp.mvc.Authentication;

/**
 * Created by WU on 17/11/2015.
 */
public class AccountInfo {
    String name;
    String password;

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    int credit;
    public AccountInfo(String name, String password) {
        this.name = name;
        this.password = password;
        this.credit = 0;
    }
    public AccountInfo() {}
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
