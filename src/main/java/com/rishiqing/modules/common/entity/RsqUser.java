package com.rishiqing.modules.common.entity;

/**
 * 日事清用户
 */
public class RsqUser {

    //账号id
    private Integer id;

    //帐号是否被锁住
    private boolean accountLocked = false;

    //用户所在公司id
    private Integer teamId;

    //用户所在公司name
    private String teamName;

    //邮箱
    private String email;

    //是否验证过邮箱
    private Boolean isCheckEmail = false;

    //密码
    private String password;

    //手机号
    private String phoneNumber;

    //头像地址后缀
    private String avatar;

    //个人设置改的名字
    private String name;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCheckEmail() {
        return isCheckEmail;
    }

    public void setCheckEmail(Boolean checkEmail) {
        isCheckEmail = checkEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
