package com.rishiqing.modules.common.mapper;

import com.rishiqing.modules.common.entity.RsqPayProduct;
import com.rishiqing.modules.common.entity.RsqTeamVersion;
import com.rishiqing.modules.common.entity.RsqUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map; /**
 * @Title: 通用service，用来获取版本号，付费字段等信息
 * @Description:
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0
 *
 */
public interface RsqCommonMapper {

    /**
     * 获取当前用户在日事清系统中对应的信息
     * @param queryMap
     * @return
     */
    RsqUser getUserInfoInRishiqingDB(@Param("params")Map<String, String> queryMap);

    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return
     */
    List<RsqUser> getUserInfoInRishiqingDBById(@Param("id")Integer userId);

    /**
     * 获取team版本信息
     */
    RsqTeamVersion getTeamVersion(@Param("versionName") String versionName);

    /**
     * 获取产品信息，通过购买版本
     */
    RsqPayProduct getRsqPayProductByTeamVersionId(@Param("teamVersionId") Integer teamVersionId);

    /**
     * 更新日事清用户密码
     */
    int updateRsqPassword(RsqUser user);
}