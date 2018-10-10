package com.rishiqing.modules.userstatistic.entity;

/**
 * @Title: 日事清后台数据统计(昨日后台数据统计)
 * @Description: 日事清后台数据统计(昨日后台数据统计)
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
public class RsqYesterdayStatistic {

	/** 昨日注册用户 */
	private int registUser;

	/** 昨日新建公司 */
	private int registTeam;

	/** 昨日登录团队 */
	private int loginTeam;

	/** 昨日登录人数 */
	private int loginUser;

	/** 昨日邀请未登陆人数 */
	private int waitLoginUser;

	/** 昨日总邀请人数 */
	private int allInviteUser;

	public int getRegistUser() {
		return registUser;
	}

	public void setRegistUser(int registUser) {
		this.registUser = registUser;
	}

	public int getRegistTeam() {
		return registTeam;
	}

	public void setRegistTeam(int registTeam) {
		this.registTeam = registTeam;
	}

	public int getLoginTeam() {
		return loginTeam;
	}

	public void setLoginTeam(int loginTeam) {
		this.loginTeam = loginTeam;
	}

	public int getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(int loginUser) {
		this.loginUser = loginUser;
	}

	public int getWaitLoginUser() {
		return waitLoginUser;
	}

	public void setWaitLoginUser(int waitLoginUser) {
		this.waitLoginUser = waitLoginUser;
	}

	public int getAllInviteUser() {
		return allInviteUser;
	}

	public void setAllInviteUser(int allInviteUser) {
		this.allInviteUser = allInviteUser;
	}
}
