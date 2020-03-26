package com.lyx.ganon.admin.model;

public class SysUser {
    private Integer id;

    private String name;

    private String password;

    private String passwordMd5;

    private String passwordSha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public String getPasswordSha() {
        return passwordSha;
    }

    public void setPasswordSha(String passwordSha) {
        this.passwordSha = passwordSha;
    }
}