package com.rishiqing.core.constant;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 日事清系统需要的常量信息
 */
public class RsqSystemConstants {

    //默认能进行操作的公司名（在日事清数据库中）
//    public static String companyName = "北京创仕科锐信息技术有限公司";

    public static Long RISHIQING_TEAM_ID = (long) 1;

    /**********************************
     *          支付相关
     **********************************/

    //永久版公司可容纳人数
    public static final Integer UNLIMITED_PERSON = -1;

    //免费版
    public static final String TEAM_FREE = "free";
    //专业版
    public static final String TEAM_VERSION_BASE_PROFESSIONAL = "BASE_PROFESSIONAL";
    //专业永久版
    public static final String TEAM_VERSION_ADVANCED_PROFESSIONAL = "ADVANCED_PROFESSIONAL";
    //企业版
    public static final String TEAM_VERSION_BASE_ENTERPRISE = "BASE_ENTERPRISE";
    //企业永久版
    public static final String TEAM_VERSION_ADVANCED_ENTERPRISE = "ADVANCED_ENTERPRISE";
    //试用专业版
    public static final String TEAM_VERSION_TRIAL_PROFESSIONAL = "TRIAL_PROFESSIONAL";
    //试用企业版
    public static final String TEAM_VERSION_TRIAL_ENTERPRISE = "TRIAL_ENTERPRISE";

    // 购买的类型
    // 加人
    public static final String ADD = "add";
    // 购买
    public static final String PAY = "pay";
    // 更新
    public static final String UPDATE = "update";
    // 续费
    public static final String RENEWALS = "renewals";

    public static final List<String> PAY_TYPE_LIST = Arrays.asList(ADD, PAY, UPDATE, RENEWALS);

    // 订单号位数
    public static final  Integer OUT_TRADE_NO_MAX_BIT = 28;

}
