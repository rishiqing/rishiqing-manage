package com.rishiqing.modules.common.service;

import com.rishiqing.modules.common.entity.RsqPayProduct;
import com.rishiqing.modules.common.entity.RsqTeamVersion;
import com.rishiqing.modules.common.entity.RsqUser;

import java.util.List;
import java.util.Map;

/**
 * @Title: 通用service，用来获取版本号，付费字段等信息
 * @Description:
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0   
 *
 */
public interface IRsqCommonService {

    /**
     * 判断当前用户是不是日事清系统中的成员（没有加公司判断，如果是）
     * @return
     */
    boolean judgeUserPermission();

    /**
     * 获取当前用户对应的日事清用户信息
     * @return
     */
    RsqUser getUserInfoInRishiqingDB();

    /**
     * 获取当前用户对应的日事清用户信息
     * @return
     */
    RsqUser getUserInfoInRishiqingDBById(Integer userId);

    /**
     * 获取当前用户对应的日事清用户列表
     * @return
     */
    List<RsqUser> listUserInfoInRishiqingDB ();

    /**
     * 通过用户名获取日事清用户信息
     * @param username
     * @return
     */
    RsqUser getUserInfoInRishiqingDBByUsername(String username);

    /**
     * 获取专业版id
     * @return
     */
    Integer getBaseProfessionalVerionId();

    /**
     * 获取企业版id
     * @return
     */
    Integer getBaseEnterpriseVersionId();

    /**
     * 获取team版本信息
     * @return
     */
    RsqTeamVersion getTeamVersion(String versionName);

    /**
     * 获取专业版产品信息
     */
    RsqPayProduct getRsqPayProductByTeamVersionId(Integer teamVersionId);

    /**
     * 更新指定用户的密码
     */
    Map updatePassword(String userId, String pwd);
}

