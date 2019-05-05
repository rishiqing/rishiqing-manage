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
    RsqUser getUserInfoInRishiqingDB(@Param("params")Map<String, Object> queryMap);

    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return
     */
    List<RsqUser> getUserInfoInRishiqingDBById(@Param("id")Integer userId);

    /**
     * 通过 teamVersion id 获取 teamVersion
     */
    RsqTeamVersion getTeamVersionById(@Param("id") Integer id);

    /**
     * 通过 type 获取 teamVersion
     */
    RsqTeamVersion getTeamVersionByType(@Param("type") String type);

    /**
     * 获取所有的 teamVersion 列表，用来做匹配
     */
    List<RsqTeamVersion> listTeamVersion();

    /**
     * 获取产品信息，通过购买版本
     */
    RsqPayProduct getRsqPayProductByTeamVersionId(@Param("teamVersionId") Integer teamVersionId);

    /**
     * 更新日事清用户密码
     */
    int updateRsqPassword(RsqUser user);

    /**
     * 获取日事清新兵营的账户
     */
    List<RsqUser> listUserInfoInRishiqingDB(@Param("params") Map<String,Object> queryMap);

    /**
     * 通过用户名获取日事清用户
     */
    RsqUser getUserInfoInRishiqingDBByUsername(@Param("params") Map<String,Object> queryMap);

}