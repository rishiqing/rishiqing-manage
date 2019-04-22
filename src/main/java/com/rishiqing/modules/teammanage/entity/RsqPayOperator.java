package com.rishiqing.modules.teammanage.entity;

public class RsqPayOperator {

    // 数据库Sql
    /*
     create table `pay_operator`(
	`id` bigint(20) not null auto_increment comment '主键字段',
	`name` varchar(30) not null comment '充值人员姓名',
	`out_trade_no` varchar(255) not null comment '订单号',
	`manage_id` varchar(100) not null comment '管理系统的用户 id',
	primary key (`id`)
    );
     */
    // 支付操作 id
    private long id;
    // 支付操作者姓名
    private String name;
    // 订单 id
    private String outTradeNo;
    // 系统管理员账户 id
    private String manageId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getManageId() {
        return manageId;
    }

    public void setManageId(String manageId) {
        this.manageId = manageId;
    }

}
