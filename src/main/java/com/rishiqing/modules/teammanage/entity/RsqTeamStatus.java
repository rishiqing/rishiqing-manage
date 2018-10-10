package com.rishiqing.modules.teammanage.entity;

import java.util.Date;

/**   
 * @Title: 日事清teamStatus
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0   
 *
 */
public class RsqTeamStatus{

    /**字段主键*/
	private String id;

	private Integer version;

	/**创建时间*/
	private Date dateCreated;

	/**更新时间*/
	private Date lastUpdated;

	/** 到期时间 */
	private Date deadLine;

	/** 用户数量 */
	private Integer userLimit;

	/** 过期 */
	private Boolean isExpired = false;

	/** 公司id */
	private Integer teamId;

	/** 版本id */
	private Integer teamVersionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Integer getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(Integer userLimit) {
		this.userLimit = userLimit;
	}

	public Boolean getExpired() {
		return isExpired;
	}

	public void setExpired(Boolean expired) {
		isExpired = expired;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamVersionId() {
		return teamVersionId;
	}

	public void setTeamVersionId(Integer teamVersionId) {
		this.teamVersionId = teamVersionId;
	}
}
