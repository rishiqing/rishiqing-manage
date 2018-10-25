package com.rishiqing.modules.teammanage.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**   
 * @Title: 团队管理
 * @Description: 团队管理
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0   
 *
 */
public interface IRsqTeamManageService extends ICommonService<RsqTeamManage> {

    Page<RsqTeamManage> ajaxList(Queryable queryable, HttpServletRequest request);

    /**
     * 通过id获取团队信息
     * @param id
     * @return
     */
    RsqTeamManage getRsqTeamManageById(String id);

    /**
     * 获取团队的会员相关信息
     */
    Map<String, String> getTeamStaus(RsqTeamManage rsqTeamManage);


    /**
     * 开通试用
     * @param paramMap
     */
    Map<String, String> rsqTry(Map<String, String> paramMap);

    /**
     * 购买
     * @param paramMap
     */
    Map<String, String> rsqBuy(Map<String, String> paramMap);

    /**
     * 续费
     * @param paramMap
     */
    Map<String, String> rsqRenewal(Map<String, String> paramMap);

    /**
     * 增加人数
     * @param paramMap
     */
    Map<String, String> rsqAdd(Map<String, String> paramMap);

    /**
     * 版本升级
     * @param paramMap
     */
    Map<String, String> rsqUpdate(Map<String, String> paramMap);

    /**
     * 当前用户操作权限判断
     * @return
     */
    boolean judgeUserPermission();
}

