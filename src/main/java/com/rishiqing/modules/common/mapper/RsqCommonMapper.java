package com.rishiqing.modules.common.mapper;

import com.rishiqing.modules.common.entity.RsqPayProduct;
import com.rishiqing.modules.common.entity.RsqTeamVersion;
import com.rishiqing.modules.common.entity.RsqUser;

import java.util.Map; /**
 * @Title: 通用service，用来获取版本号，付费字段等信息
 * @Description:
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0
 *
 */
public interface RsqCommonMapper{

    /**
     * 获取当前用户在日事清系统中对应的信息
     * @param queryMap
     * @return
     */
    RsqUser getUserInfoInRishiqingDB(Map<String, String> queryMap);

    /**
     * 获取team版本信息
     */
    RsqTeamVersion getTeamVersion(String versionName);

    /**
     * 获取产品信息，通过购买版本
     */
    RsqPayProduct getPayProduct(String versionName);
}