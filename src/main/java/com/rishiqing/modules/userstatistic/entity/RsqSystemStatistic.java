package com.rishiqing.modules.userstatistic.entity;

/**
 * @Title: 日事清后台数据统计
 * @Description: 日事清后台数据统计
 *
 * 后台需要数据：
    共有用户:	5050	个人用户:	2136	公司用户:	2914	邀请未登录人数:	0
	共有公司:	1852	正式公司:	99	失效公司:	352	总邀请人数:	442
	当前在线人数:	0
	昨日注册用户:	4	昨日新建公司:	0
	今日注册用户:	3	今日新建公司:	0
	昨日登录团队		昨日登录人数
	今日登录团队	1	今日登录用户	6
	昨日邀请未登陆人数:	0	今日邀请未登陆人数:	0
	昨日总邀请人数:	0	今日总邀请人数:	0


 * 钉钉数据：
	钉钉用户总数:	127	钉钉企业总数:	13
	昨日钉钉注册用户:	1	昨日钉钉新建公司:	0
	今日钉钉注册用户:	0	今日钉钉新建公司:	0

 * @author rishiqing
 * @date 2018-09-18 14:47:12
 * @version V1.0   
 *
 */
public class RsqSystemStatistic{

	/*
	 * 系统数据
	 */
	/** 共有用户 */
	private int totalUser;

	/** 个人用户 */
	private int singleUser;

	/** 公司用户 */
	private int teamUser;

	/** 邀请未登录人数 */
	private int waitLoginUser;

	/** 共有公司 */
	private int totalTeam;

	/** 正式公司 */
	private int effectiveTeam;

	/** 失效公司 */
	private int invaildTeam;

	/** 总邀请人数 */
	private int allInviteUser;

	/** 当前在线人数 */
	private int currentLoginUser;

	/*
	 * 今日后台数据统计
	 */
	private RsqDayStatistic todayStatistic;

	/*
	 * 昨日后台数据统计
	 */
	private RsqDayStatistic yesterdayStatistic;

	/*
	 * 钉钉数据统计
	 */
	private RsqDingStatistic rsqDingStatistic;

	public int getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
	}

	public int getSingleUser() {
		return singleUser;
	}

	public void setSingleUser(int singleUser) {
		this.singleUser = singleUser;
	}

	public int getTeamUser() {
		return teamUser;
	}

	public void setTeamUser(int teamUser) {
		this.teamUser = teamUser;
	}

	public int getWaitLoginUser() {
		return waitLoginUser;
	}

	public void setWaitLoginUser(int waitLoginUser) {
		this.waitLoginUser = waitLoginUser;
	}

	public int getTotalTeam() {
		return totalTeam;
	}

	public void setTotalTeam(int totalTeam) {
		this.totalTeam = totalTeam;
	}

	public int getEffectiveTeam() {
		return effectiveTeam;
	}

	public void setEffectiveTeam(int effectiveTeam) {
		this.effectiveTeam = effectiveTeam;
	}

	public int getInvaildTeam() {
		return invaildTeam;
	}

	public void setInvaildTeam(int invaildTeam) {
		this.invaildTeam = invaildTeam;
	}

	public int getAllInviteUser() {
		return allInviteUser;
	}

	public void setAllInviteUser(int allInviteUser) {
		this.allInviteUser = allInviteUser;
	}

	public int getCurrentLoginUser() {
		return currentLoginUser;
	}

	public void setCurrentLoginUser(int currentLoginUser) {
		this.currentLoginUser = currentLoginUser;
	}

	public RsqDayStatistic getTodayStatistic() {
		return todayStatistic;
	}

	public void setTodayStatistic(RsqDayStatistic todayStatistic) {
		this.todayStatistic = todayStatistic;
	}

	public RsqDayStatistic getYesterdayStatistic() {
		return yesterdayStatistic;
	}

	public void setYesterdayStatistic(RsqDayStatistic yesterdayStatistic) {
		this.yesterdayStatistic = yesterdayStatistic;
	}

	public RsqDingStatistic getRsqDingStatistic() {
		return rsqDingStatistic;
	}

	public void setRsqDingStatistic(RsqDingStatistic rsqDingStatistic) {
		this.rsqDingStatistic = rsqDingStatistic;
	}
}
