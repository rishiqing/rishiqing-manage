package com.rishiqing.modules.useradvice.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;

import java.util.Date;

/**
 * 用户意见
 */
public class RsqUserAdvice extends AbstractEntity<String> {

    /**字段主键*/
    private String id;

    /**订单创建时间*/
    private Date dateCreated = new Date();

    //哪个端提的建议
    private String client;

    //意见
    private String remark;

    //打分
    private String star;

    //提意见的人
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
