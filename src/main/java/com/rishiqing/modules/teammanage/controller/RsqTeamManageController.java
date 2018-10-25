package com.rishiqing.modules.teammanage.controller;

import cn.jeeweb.core.common.data.DuplicateValid;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.model.ValidJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.rishiqing.modules.teammanage.entity.RsqTeamStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.rishiqing.core.common.controller.RsqBaseBeanController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.teammanage.service.IRsqTeamManageService;

/**   
 * @Title: 团队管理
 * @Description: 团队管理
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/teammanage/rsqteammanage")
@RequiresPathPermission("teammanage:rsqteammanage")
public class RsqTeamManageController extends RsqBaseBeanController<RsqTeamManage> {

    @Autowired
    protected IRsqTeamManageService rsqTeamManageService;

    public RsqTeamManage get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return rsqTeamManageService.getRsqTeamManageById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

    /**
     * 获取团队管理列表
     * @param queryable
     * @param propertyPreFilterable
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        PageJson<RsqTeamManage> pagejson = new PageJson<>(rsqTeamManageService.ajaxList(queryable,request));
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        String content = JSON.toJSONString(pagejson, filter);

        StringUtils.printJson(response, content);
    }

    /**
     * 跳转到充值页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "pay", method = RequestMethod.GET)
    public String pay(Model model, HttpServletRequest request, HttpServletResponse response) {
        //获取要充值的团队id
        String id = request.getParameter("id");
        //根据id获取团队信息
        RsqTeamManage rsqTeamManage = rsqTeamManageService.getRsqTeamManageById(id);
        //获取团队的会员信息
        Map<String, String> teamStatusMap = rsqTeamManageService.getTeamStaus(rsqTeamManage);
        model.addAttribute("rsqTeamManage", rsqTeamManage);
        model.addAttribute("teamStatusMap", teamStatusMap);
        return display("pay");
    }

    /**
     * 开通试用
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "rsqTry", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, String> rsqTry(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> resMap = new HashMap<>();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                resMap.put("fail", "当前用户没有操作权限！");
                return resMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "开通试用失败，请联系管理员进行查看!");
            return resMap;
        }

        Map<String, String> paramMap = paramsDeal(request);

        try {
            resMap = rsqTeamManageService.rsqTry(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "开通试用失败，请联系管理员进行查看!");
        }
        return resMap;
    }

    /**
     * 购买
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "rsqBuy", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, String> rsqBuy(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> resMap = new HashMap<>();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                resMap.put("fail", "当前用户没有操作权限！");
                return resMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "开通试用失败，请联系管理员进行查看!");
            return resMap;
        }

        Map<String, String> paramMap = paramsDeal(request);
        try {
            resMap = rsqTeamManageService.rsqBuy(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "购买失败，请联系管理员进行查看!");
        }
        return resMap;
    }

    /**
     * 续费
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "rsqRenewal", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, String> rsqRenewal(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> resMap = new HashMap<>();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                resMap.put("fail", "当前用户没有操作权限！");
                return resMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "开通试用失败，请联系管理员进行查看!");
            return resMap;
        }

        Map<String, String> paramMap = paramsDeal(request);
        try {
            resMap = rsqTeamManageService.rsqRenewal(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "续费失败，请联系管理员进行查看!");
        }
        return resMap;
    }

    /**
     * 增加人数
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "rsqAdd", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, String> rsqAdd(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> resMap = new HashMap<>();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                resMap.put("fail", "当前用户没有操作权限！");
                return resMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "开通试用失败，请联系管理员进行查看!");
            return resMap;
        }

        Map<String, String> paramMap = paramsDeal(request);
        try {
            resMap = rsqTeamManageService.rsqAdd(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "增加人数失败，请联系管理员进行查看!");
        }

        return resMap;
    }

    /**
     * 版本升级
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "rsqUpdate", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, String> rsqUpdate(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> resMap = new HashMap<>();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                resMap.put("fail", "当前用户没有操作权限！");
                return resMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "开通试用失败，请联系管理员进行查看!");
            return resMap;
        }

        Map<String, String> paramMap = paramsDeal(request);
        try {
            resMap = rsqTeamManageService.rsqUpdate(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            resMap.put("fail", "版本升级失败，请联系管理员进行查看!");
        }

        return resMap;
    }

    /**
     * 处理请求参数
     * @param request
     * @return
     */
    private Map<String, String> paramsDeal(HttpServletRequest request){
        Map<String, String> paramMap = new HashMap<>();
        //获取团队id
        String id = request.getParameter("teamId");
        paramMap.put("id", id);
        //获取版本信息
        String buyVersion = request.getParameter("buyTypeRadio");
        paramMap.put("buyVersion", buyVersion);
        //获取购买人数
        String buyNumbers = request.getParameter("teamNumber");
        paramMap.put("buyNumbers", buyNumbers);
        //获取购买天数
        String buyDays = request.getParameter("buyDay");
        paramMap.put("buyDays", buyDays);
        //购买费用
        String totalFee = request.getParameter("totalFee");
        paramMap.put("totalFee", totalFee);
        return paramMap;
    }
}
