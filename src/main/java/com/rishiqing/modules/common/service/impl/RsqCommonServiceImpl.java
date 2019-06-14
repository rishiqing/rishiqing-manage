package com.rishiqing.modules.common.service.impl;

import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.security.shiro.realm.UserRealm;
import cn.jeeweb.modules.sys.service.IUserService;
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

import java.util.ArrayList;
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

    @Autowired
    private IUserService userService;

    @Override
    public boolean judgeUserPermission() {
        RsqUser rsqUser = getUserInfoInRishiqingDB();
        if (rsqUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public RsqUser getUserInfoInRishiqingDB(){
        //获取当前用户信息
        UserRealm.Principal principal = UserUtils.getPrincipal();
        String username = principal.getUsername();
        User user = userService.findByUsername(username);
        if (user == null) {
            return null;
        }
        String rsqUsername = user.getRsqUsername();
        RsqUser userInfo = getUserInfoInRishiqingDBByUsername(rsqUsername);
        if(userInfo == null){
            return null;
        }
        if (!userInfo.getTeamId().toString().equals(RsqSystemConstants.RISHIQING_TEAM_ID.toString())) {
            return null;
        }
        return userInfo;
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
    @Deprecated
    public List<RsqUser> listUserInfoInRishiqingDB() {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("teamId", RsqSystemConstants.RISHIQING_TEAM_ID);
        List<RsqUser> rsqUsers = rsqCommonMapper.listUserInfoInRishiqingDB(queryMap);
        if (rsqUsers.size() == 0) {
            return new ArrayList<>();
        }
        return rsqUsers;
    }

    @Override
    public RsqUser getUserInfoInRishiqingDBByUsername(String username) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("username",username);
        queryMap.put("teamId", RsqSystemConstants.RISHIQING_TEAM_ID);
        RsqUser rsqUser = rsqCommonMapper.getUserInfoInRishiqingDBByUsername(queryMap);
        return rsqUser;
    }

    @Override
    public Integer getBaseProfessionalVerionId() {
        return getTeamVersionByType(RsqSystemConstants.TEAM_VERSION_BASE_PROFESSIONAL).getId();
    }

    @Override
    public Integer getBaseEnterpriseVersionId() {
        return getTeamVersionByType(RsqSystemConstants.TEAM_VERSION_BASE_ENTERPRISE).getId();
    }

    @Override
    public RsqTeamVersion getTeamVersionById(Integer id) {
        return rsqCommonMapper.getTeamVersionById(id);
    }

    @Override
    public RsqTeamVersion getTeamVersionByType (String type) {
        return rsqCommonMapper.getTeamVersionByType(type);
    }

    @Override
    public List<RsqTeamVersion> listTeamVersion () {
        return rsqCommonMapper.listTeamVersion();
    }

    @Override
    public RsqPayProduct getRsqPayProductByTeamVersionId(Integer teamVersionId) {
        return rsqCommonMapper.getRsqPayProductByTeamVersionId(teamVersionId);
    }

    @Override
    public List<RsqPayProduct> listRsqPayProduct() {
        return rsqCommonMapper.listRsqPayProduct();
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

