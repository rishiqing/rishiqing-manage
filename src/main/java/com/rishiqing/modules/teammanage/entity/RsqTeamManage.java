package com.rishiqing.modules.teammanage.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import cn.jeeweb.modules.sys.entity.User;
import java.util.Date;

/**   
 * @Title: 团队管理
 * @Description: 团队管理
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0   
 *
 */
@TableName("rsq_team_manage")
@SuppressWarnings("serial")
public class RsqTeamManage extends AbstractEntity<String> {

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
    /**名称*/
    @TableField(value = "team_name")
	private String teamName;
    /**创建者*/
    @TableField(value = "create_man_name")
	private String createManName;
    /**联系邮箱*/
    @TableField(value = "linked_email")
	private String linkedEmail;
    /**联系电话*/
    @TableField(value = "linked_phone")
	private String linkedPhone;
    /**成员数*/
    @TableField(value = "team_member")
	private Integer teamMember;
	
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
	 * 获取  teamName
	 *@return: String  名称
	 */
	public String getTeamName(){
		return this.teamName;
	}

	/**
	 * 设置  teamName
	 *@param: teamName  名称
	 */
	public void setTeamName(String teamName){
		this.teamName = teamName;
	}
	/**
	 * 获取  createManName
	 *@return: String  创建者
	 */
	public String getCreateManName(){
		return this.createManName;
	}

	/**
	 * 设置  createManName
	 *@param: createManName  创建者
	 */
	public void setCreateManName(String createManName){
		this.createManName = createManName;
	}
	/**
	 * 获取  linkedEmail
	 *@return: String  联系邮箱
	 */
	public String getLinkedEmail(){
		return this.linkedEmail;
	}

	/**
	 * 设置  linkedEmail
	 *@param: linkedEmail  联系邮箱
	 */
	public void setLinkedEmail(String linkedEmail){
		this.linkedEmail = linkedEmail;
	}
	/**
	 * 获取  linkedPhone
	 *@return: String  联系电话
	 */
	public String getLinkedPhone(){
		return this.linkedPhone;
	}

	/**
	 * 设置  linkedPhone
	 *@param: linkedPhone  联系电话
	 */
	public void setLinkedPhone(String linkedPhone){
		this.linkedPhone = linkedPhone;
	}
	/**
	 * 获取  teamMember
	 *@return: Integer  成员数
	 */
	public Integer getTeamMember(){
		return this.teamMember;
	}

	/**
	 * 设置  teamMember
	 *@param: teamMember  成员数
	 */
	public void setTeamMember(Integer teamMember){
		this.teamMember = teamMember;
	}
	
}
