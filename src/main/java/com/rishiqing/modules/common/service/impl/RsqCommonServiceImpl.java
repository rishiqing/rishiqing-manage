package com.rishiqing.modules.common.service.impl;

import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm;
import cn.jeeweb.modules.sys.utils.PhoneFormatCheckUtils;
import cn.jeeweb.modules.sys.utils.UserUtils;
import com.rishiqing.core.constant.RsqSystemConstants;
import com.rishiqing.core.util.SHAUtil;
import com.rishiqing.modules.common.entity.RsqPayProduct;
import com.rishiqing.modules.common.entity.RsqTeamVersion;
import com.rishiqing.modules.common.entity.RsqUser;
import com.rishiqing.modules.common.mapper.RsqCommonMapper;
import com.rishiqing.modules.common.service.IRsqCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 团队管理
 * @Description: 团队管理
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0
 *
 */
@Transactional
@Service("rsqCommonService")
public class RsqCommonServiceImpl implements IRsqCommonService {

    @Autowired
    private RsqCommonMapper rsqCommonMapper;

    @Override
    public boolean judgeUserPermission() {
        RsqUser userInfo = getUserInfoInRishiqingDB();
        if(userInfo != null){
            return true;
        }
        return false;
    }

    @Override
    public RsqUser getUserInfoInRishiqingDB(){
        //获取当前用户信息
        UserRealm.Principal principal = UserUtils.getPrincipal();
        String username = principal.getUsername();
        //如果用户名是邮箱格式，按邮箱查询，否则按手机号查
        Map<String, String> queryMap = new HashMap<>();
        if(username.contains("@")){
            queryMap.put("email", username);
        }else{
            if(PhoneFormatCheckUtils.isChinaPhoneLegal(username)){
                queryMap.put("phone", username);
            }else{
                return null;
            }
        }
        queryMap.put("teamName", RsqSystemConstants.companyName);
        return rsqCommonMapper.getUserInfoInRishiqingDB(queryMap);
    }

    @Override
    public RsqUser getUserInfoInRishiqingDBById(Integer userId) {
        List<RsqUser> rsqUserList = rsqCommonMapper.getUserInfoInRishiqingDBById(userId);
        if(rsqUserList != null && rsqUserList.size() > 0){
            return rsqUserList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer getBaseProfessionalVerionId() {
        return getTeamVersion(RsqSystemConstants.BASE_PROFESSIONAL).getId();
    }

    @Override
    public Integer getBaseEnterpriseVersionId() {
        return getTeamVersion(RsqSystemConstants.BASE_ENTERPRISE).getId();
    }

    @Override
    public RsqTeamVersion getTeamVersion(String versionName) {
        return rsqCommonMapper.getTeamVersion(versionName);
    }

    @Override
    public RsqPayProduct getRsqPayProductByTeamVersionId(Integer teamVersionId) {
        return rsqCommonMapper.getRsqPayProductByTeamVersionId(teamVersionId);
    }

    @Override
    public Map updatePassword(String userId, String pwd) {
        Map resMap = new HashMap();
        resMap.put("flag", false);
        List<RsqUser> resUserList = this.rsqCommonMapper.getUserInfoInRishiqingDBById(Integer.parseInt(userId));
        if(resUserList == null || resUserList.size() == 0){
            return resMap;
        }
        RsqUser user = resUserList.get(0);
        //加密
        String password = SHAUtil.SHA512(pwd);
        user.setPassword(password);
        this.rsqCommonMapper.updateRsqPassword(user);
        resMap.put("flag", true);
        return resMap;
    }
}

