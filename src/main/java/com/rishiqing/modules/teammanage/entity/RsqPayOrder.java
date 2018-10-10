package com.rishiqing.modules.teammanage.entity;

import java.util.Date;

/**
 * 日事清充值记录表
 */
public class RsqPayOrder {

    //默认都是0
    private Integer version = 0;

	/**订单创建时间*/
    private Date dateCreated = new Date();

    /**订单最后更新时间*/
    private Date lastUpdated = new Date();

    /**订单说明*/
    private String body;

    /**
     * 选择支付的平台, 后台充值默认”系统“
     可选范围："微信","支付宝","iOS 内购充值","ping++","系统","0元支付","旧版迁移"
      */
    private String platform = "系统";

    /**购买天数*/
    private Integer days;

    /** teamid */
    private Integer teamId;

    /**购买的产品，续费，加人，不填此字段*/
    private Integer payProductId;

    /**订单总价*/
    private Double totalFee;

    /**购买人数：升级，续费，不填写此字段*/
    private Integer userLimit;

    /** 创建人id */
    private Integer userId;

    /**
     * 发起支付的客户端,后台充值默认“sys”
     * "web", "android", "ios", "sys", "unknown"
     */
    private String client = "sys";

    /**
     * 订单状态：
     * true 购买成功的订单
     * false 因某种原因没有购买成功
     */
    private Boolean status = true;

    /**
     * 支付类型，目前支持
     * pay 购买
     * add 加人
     * renewals 续费
     * update 升级
     */
    private String payType;

    /** 内部交易号 */
    private String transactNo;

    /**订单号*/
    private String outTradeNo;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getPayProductId() {
        return payProductId;
    }

    public void setPayProductId(Integer payProductId) {
        this.payProductId = payProductId;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTransactNo() {
        return transactNo;
    }

    public void setTransactNo(String transactNo) {
        this.transactNo = transactNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
