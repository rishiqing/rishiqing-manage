package com.rishiqing.modules.common.entity;

import java.util.Date;

/**
 * team信息
 13	0	2018-07-19 05:17:04	2018-07-19 05:17:04	BASE_PROFESSIONAL
 14	0	2018-07-19 05:17:04	2018-07-19 05:17:04	ADVANCED_PROFESSIONAL
 15	0	2018-07-19 05:17:04	2018-07-19 05:17:04	TRIAL_PROFESSIONAL
 16	0	2018-07-19 05:17:04	2018-07-19 05:17:04	BASE_ENTERPRISE
 17	0	2018-07-19 05:17:04	2018-07-19 05:17:04	ADVANCED_ENTERPRISE
 18	0	2018-07-19 05:17:04	2018-07-19 05:17:04	TRIAL_ENTERPRISE
 */
public class RsqTeamVersion {

    Integer id;

    //创建时间
    Date dateCreated;
    //更新时间
    Date lastUpdated;

    /**
     * 类型：
     * BASE_PROFESSIONAL-专业版
     * ADVANCED_PROFESSIONAL-专业永久版
     * BASE_ENTERPRISE-企业版
     * ADVANCED_ENTERPRISE-企业永久版
     * TRIAL_PROFESSIONAL-试用专业版
     * TRIAL_ENTERPRISE-试用企业版
     * */
    String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
