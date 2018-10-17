package com.rishiqing.modules.userstatistic.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jeeweb.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 日事清用户数据统计
 * @Description: 日事清用户数据统计
 * @author rishiqing
 * @date 2018-09-18 14:47:12
 * @version V1.0   
 *
 */
@TableName("rsq_user_statistic")
@SuppressWarnings("serial")
public class RsqUserStatistic extends AbstractEntity<String> {

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
    /**姓名*/
    @TableField(value = "name")
	private String name;
    /**邮箱*/
    @TableField(value = "email")
	private String email;
    /**手机号*/
    @TableField(value = "phone")
	private String phone;
    /**注册时间*/
    @TableField(value = "regist_date")
	private Date registDate;
    /**最后登录时间*/
    @TableField(value = "last_login_date")
	private Date lastLoginDate;
    /**公司名称*/
    @TableField(value = "team_name")
	private String teamName;
    /**公司人数*/
    @TableField(value = "team_number")
	private Integer teamNumber;
    /**数据来源*/
    @TableField(value = "date_from")
	private String dateFrom;
    /**渠道*/
    @TableField(value = "date_channel")
	private String dateChannel;
    /**用户类型*/
    @TableField(value = "user_type")
	private String userType;

	/**
	 * 登录次数
	 */
	@TableField(value = "login_cnt")
	private Integer loginCnt;

	@TableField(value = "account_locked")
	private Boolean accountLocked;

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	//用户id
	@TableField(value = "userId")
	private Integer userId;

	//公司id
	@TableField(value = "teamId")
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

	public Integer getLoginCnt() {
		return loginCnt;
	}

	public void setLoginCnt(Integer loginCnt) {
		this.loginCnt = loginCnt;
	}

	/**
	 * 注册天数
     */
    private String registDay;

	public String getRegistDay() {
		return registDay;
	}

	public void setRegistDay(String registDay) {
		this.registDay = registDay;
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
	 * 获取  name
	 *@return: String  姓名
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 设置  name
	 *@param: name  姓名
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 获取  email
	 *@return: String  邮箱
	 */
	public String getEmail(){
		return this.email;
	}

	/**
	 * 设置  email
	 *@param: email  邮箱
	 */
	public void setEmail(String email){
		this.email = email;
	}
	/**
	 * 获取  phone
	 *@return: String  手机号
	 */
	public String getPhone(){
		return this.phone;
	}

	/**
	 * 设置  phone
	 *@param: phone  手机号
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	/**
	 * 获取  registDate
	 *@return: Date  注册时间
	 */
	public Date getRegistDate(){
		return this.registDate;
	}

	/**
	 * 设置  registDate
	 *@param: registDate  注册时间
	 */
	public void setRegistDate(Date registDate){
		this.registDate = registDate;
	}
	/**
	 * 获取  lastLoginDate
	 *@return: Date  最后登录时间
	 */
	public Date getLastLoginDate(){
		return this.lastLoginDate;
	}

	/**
	 * 设置  lastLoginDate
	 *@param: lastLoginDate  最后登录时间
	 */
	public void setLastLoginDate(Date lastLoginDate){
		this.lastLoginDate = lastLoginDate;
	}
	/**
	 * 获取  teamName
	 *@return: String  公司名称
	 */
	public String getTeamName(){
		return this.teamName;
	}

	/**
	 * 设置  teamName
	 *@param: teamName  公司名称
	 */
	public void setTeamName(String teamName){
		this.teamName = teamName;
	}
	/**
	 * 获取  teamNumber
	 *@return: Integer  公司人数
	 */
	public Integer getTeamNumber(){
		return this.teamNumber;
	}

	/**
	 * 设置  teamNumber
	 *@param: teamNumber  公司人数
	 */
	public void setTeamNumber(Integer teamNumber){
		this.teamNumber = teamNumber;
	}
	/**
	 * 获取  dateFrom
	 *@return: String  数据来源
	 */
	public String getDateFrom(){
		return this.dateFrom;
	}

	/**
	 * 设置  dateFrom
	 *@param: dateFrom  数据来源
	 */
	public void setDateFrom(String dateFrom){
		this.dateFrom = dateFrom;
	}
	/**
	 * 获取  dateChannel
	 *@return: String  渠道
	 */
	public String getDateChannel(){
		return this.dateChannel;
	}

	/**
	 * 设置  dateChannel
	 *@param: dateChannel  渠道
	 */
	public void setDateChannel(String dateChannel){
		this.dateChannel = dateChannel;
	}
	/**
	 * 获取  userType
	 *@return: String  用户类型
	 */
	public String getUserType(){
		return this.userType;
	}

	/**
	 * 设置  userType
	 *@param: userType  用户类型
	 */
	public void setUserType(String userType){
		this.userType = userType;
	}
	
}
