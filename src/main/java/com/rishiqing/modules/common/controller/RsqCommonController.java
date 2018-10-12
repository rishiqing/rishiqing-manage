package com.rishiqing.modules.common.controller;

import cn.jeeweb.core.common.data.DuplicateValid;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.model.ValidJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.core.utils.security.Md5Utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.rishiqing.core.common.controller.RsqBaseBeanController;
import com.rishiqing.core.util.AESUtil;
import com.rishiqing.modules.common.entity.RsqUser;
import com.rishiqing.modules.common.service.IRsqCommonService;
import com.rishiqing.modules.payrecord.entity.RsqPayRecord;
import com.rishiqing.modules.payrecord.service.IRsqPayRecordService;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.teammanage.service.IRsqTeamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**   
 * @Title: 充值消费记录
 * @Description: 充值消费记录
 * @author rishiqing
 * @date 2018-09-20 10:29:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/common/rsqcommon")
public class RsqCommonController extends RsqBaseBeanController<RsqPayRecord> {

    @Autowired
    private IRsqCommonService rsqCommonService;
    @Autowired
    private IRsqTeamManageService rsqTeamManageService;

    /**
     * 跳转到公司页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "openCompany", method = RequestMethod.GET)
    public String openCompany(Model model, HttpServletRequest request, HttpServletResponse response) {
        //获取要充值的团队id
        String id = request.getParameter("id");
        //根据id获取团队信息
        RsqTeamManage rsqTeamManage = rsqTeamManageService.getRsqTeamManageById(id);
        model.addAttribute("rsqTeamManage", rsqTeamManage);
        return display("company");
    }

    /**
     * 跳转到充值页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "openUser", method = RequestMethod.GET)
    public String openUser(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //操作权限校验
        boolean flag = rsqTeamManageService.judgeUserPermission();
        if(!flag){
            return display("unauthorized");
        }

        //获取用户id
        String id = request.getParameter("id");
        //根据用户id获取用户信息
        RsqUser rsqUser = rsqCommonService.getUserInfoInRishiqingDBById(Integer.parseInt(id));

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = getClass().getClassLoader().getResourceAsStream("user-login-url.properties");

        // 使用properties对象加载输入流
        properties.load(in);
        //获取key对应的value值
        String url = properties.getProperty("loginUrl");
        String key = properties.getProperty("adminKey");
        String targetUrl = url;
        if(rsqUser == null){
            return display("unauthorized");
        }else{
            targetUrl += "?token=" + AESUtil.encrypt(rsqUser.getUsername(), key);
        }
        return "redirect:" + targetUrl;
    }
}
