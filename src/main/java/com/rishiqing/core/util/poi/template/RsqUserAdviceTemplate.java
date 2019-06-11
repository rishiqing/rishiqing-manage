package com.rishiqing.core.util.poi.template;

import com.rishiqing.core.util.poi.annotation.ExcelField;

public class RsqUserAdviceTemplate {

    @ExcelField(title = "用户id", align = 2, sort = 10)
    private String userId;

    @ExcelField(title = "邮件", align = 2, sort = 10)
    private String email;

    @ExcelField(title = "电话号", align = 2, sort = 10)
    private String phoneNumber;

    @ExcelField(title = "渠道", align = 2, sort = 10)
    private String client;

    @ExcelField(title = "是否会员", align = 2, sort = 10)
    private String vip;

    @ExcelField(title = "评分", align = 2, sort = 10)
    private String star;

    @ExcelField(title = "评分类型", align = 2, sort = 10)
    private String type;

    @ExcelField(title = "意见", align = 2, sort = 10)
    private String remark;

    @ExcelField(title = "创建时间", align = 2, sort = 10)
    private String dateCreated;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
