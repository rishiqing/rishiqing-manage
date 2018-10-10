package com.rishiqing.modules.userstatistic.entity;

import cn.jeeweb.modules.sys.entity.User;

import java.util.Date;

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
public class RsqDingStatistic {

	/** 钉钉用户总数 */
	int userTotal;

	/** 钉钉企业总数 */
	int teamTotal;

	/** 昨日钉钉注册用户 */
	int yesterdayRegistUser;

	/** 昨日钉钉新建公司 */
	int yesterdayRegistTeam;

	/** 今日钉钉注册用户 */
	int todayRegistUser;

	/** 今日钉钉新建公司 */
	int todayRegistTeam;

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}

	public int getTeamTotal() {
		return teamTotal;
	}

	public void setTeamTotal(int teamTotal) {
		this.teamTotal = teamTotal;
	}

	public int getYesterdayRegistUser() {
		return yesterdayRegistUser;
	}

	public void setYesterdayRegistUser(int yesterdayRegistUser) {
		this.yesterdayRegistUser = yesterdayRegistUser;
	}

	public int getYesterdayRegistTeam() {
		return yesterdayRegistTeam;
	}

	public void setYesterdayRegistTeam(int yesterdayRegistTeam) {
		this.yesterdayRegistTeam = yesterdayRegistTeam;
	}

	public int getTodayRegistUser() {
		return todayRegistUser;
	}

	public void setTodayRegistUser(int todayRegistUser) {
		this.todayRegistUser = todayRegistUser;
	}

	public int getTodayRegistTeam() {
		return todayRegistTeam;
	}

	public void setTodayRegistTeam(int todayRegistTeam) {
		this.todayRegistTeam = todayRegistTeam;
	}
}
