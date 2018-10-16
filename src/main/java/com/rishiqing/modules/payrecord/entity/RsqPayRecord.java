package com.rishiqing.modules.payrecord.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jeeweb.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 充值消费记录
 * @Description: 充值消费记录
 * @author rishiqing
 * @date 2018-09-20 10:29:50
 * @version V1.0   
 *
 */
@TableName("rsq_pay_record")
@SuppressWarnings("serial")
public class RsqPayRecord extends AbstractEntity<String> {

    /**字段主键*/
    @TableId(value = "id", type = IdType.UUID)
	private String id;
    /**创建者*/
    @TableField(value = "create_by",el="createBy.id",fill = FieldFill.INSERT)
	private User createBy;
    /**创建时间*/
    @TableField(value = "create_date",fill = FieldFill.INSERT)
	private Date createDate;
    /**更新者*/
    @TableField(value = "update_by",el="updateBy.id",fill = FieldFill.UPDATE)
	private User updateBy;
    /**更新时间*/
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
    @TableField(value = "del_flag")
	private String delFlag;
    /**备注信息*/
    @TableField(value = "remarks")
	private String remarks;
    /**充值时间*/
    @TableField(value = "pay_date")
	private Date payDate;
    /**用户类型*/
    @TableField(value = "pay_type")
	private String payType;
    /**充值类型*/
    @TableField(value = "plateform")
	private String plateform;
    /**价格（自付费）*/
    @TableField(value = "price")
	private String price;
    /**团队/个人名称*/
    @TableField(value = "team_name")
	private String teamName;
    /**联系人/用户名*/
    @TableField(value = "contacts")
	private String contacts;
    /**电话*/
    @TableField(value = "linked_phone")
	private String linkedPhone;
    /**当前充值后到期时间*/
    @TableField(value = "deadline")
	private Date deadline;
    /**充值天数*/
    @TableField(value = "days")
	private String days;
    /** 充值记录描述 */
    @TableField(value = "desc")
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	//用户id
    private Integer userId;
    //公司id
    private Integer teamId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * 获取  id
	 *@return: String  字段主键
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  字段主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  createBy
	 *@return: User  创建者
	 */
	public User getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建者
	 */
	public void setCreateBy(User createBy){
		this.createBy = createBy;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建时间
	 */
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateBy
	 *@return: User  更新者
	 */
	public User getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新者
	 */
	public void setUpdateBy(User updateBy){
		this.updateBy = updateBy;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  更新时间
	 */
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  delFlag
	 *@return: String  删除标记（0：正常；1：删除）
	 */
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  删除标记（0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注信息
	 */
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注信息
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	/**
	 * 获取  payDate
	 *@return: Date  充值时间
	 */
	public Date getPayDate(){
		return this.payDate;
	}

	/**
	 * 设置  payDate
	 *@param: payDate  充值时间
	 */
	public void setPayDate(Date payDate){
		this.payDate = payDate;
	}
	/**
	 * 获取  payType
	 *@return: String  用户类型
	 */
	public String getPayType(){
		return this.payType;
	}

	/**
	 * 设置  payType
	 *@param: payType  用户类型
	 */
	public void setPayType(String payType){
		this.payType = payType;
	}
	/**
	 * 获取  plateform
	 *@return: String  充值类型
	 */
	public String getPlateform(){
		return this.plateform;
	}

	/**
	 * 设置  plateform
	 *@param: plateform  充值类型
	 */
	public void setPlateform(String plateform){
		this.plateform = plateform;
	}
	/**
	 * 获取  price
	 *@return: String  价格（自付费）
	 */
	public String getPrice(){
		return this.price;
	}

	/**
	 * 设置  price
	 *@param: price  价格（自付费）
	 */
	public void setPrice(String price){
		this.price = price;
	}
	/**
	 * 获取  teamName
	 *@return: String  团队/个人名称
	 */
	public String getTeamName(){
		return this.teamName;
	}

	/**
	 * 设置  teamName
	 *@param: teamName  团队/个人名称
	 */
	public void setTeamName(String teamName){
		this.teamName = teamName;
	}
	/**
	 * 获取  contacts
	 *@return: String  联系人/用户名
	 */
	public String getContacts(){
		return this.contacts;
	}

	/**
	 * 设置  contacts
	 *@param: contacts  联系人/用户名
	 */
	public void setContacts(String contacts){
		this.contacts = contacts;
	}
	/**
	 * 获取  linkedPhone
	 *@return: String  电话
	 */
	public String getLinkedPhone(){
		return this.linkedPhone;
	}

	/**
	 * 设置  linkedPhone
	 *@param: linkedPhone  电话
	 */
	public void setLinkedPhone(String linkedPhone){
		this.linkedPhone = linkedPhone;
	}
	/**
	 * 获取  deadline
	 *@return: Date  当前充值后到期时间
	 */
	public Date getDeadline(){
		return this.deadline;
	}

	/**
	 * 设置  deadline
	 *@param: deadline  当前充值后到期时间
	 */
	public void setDeadline(Date deadline){
		this.deadline = deadline;
	}
	/**
	 * 获取  days
	 *@return: String  充值天数
	 */
	public String getDays(){
		return this.days;
	}

	/**
	 * 设置  days
	 *@param: days  充值天数
	 */
	public void setDays(String days){
		this.days = days;
	}
	
}
