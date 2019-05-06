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
import cn.jeeweb.modules.sys.entity.User;
import cn.jeeweb.modules.sys.utils.UserUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.rishiqing.core.constant.RsqSystemConstants;
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
import java.util.ArrayList;
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
//        //获取团队的会员信息
//        Map<String, String> teamStatusMap = rsqTeamManageService.getTeamStaus(rsqTeamManage);
//        model.addAttribute("rsqTeamManage", rsqTeamManage);
//        model.addAttribute("teamStatusMap", teamStatusMap);

        // 获取所有的公司状态
        List<Map<String, String>> teamStatusList = rsqTeamManageService.listTeamStatus(rsqTeamManage);
        model.addAttribute("rsqTeamManage", rsqTeamManage);
        model.addAttribute("rsqTeamInfoList", teamStatusList);
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
        // 参数验证
        Map<String, Object> paramMap;
        try {
            paramMap = paramsDeal(request);
        } catch (Exception e) {
            resMap.put("fail", e.getMessage());
            return resMap;
        }

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

        // 参数验证
        Map<String, Object> paramMap;
        try {
            paramMap = paramsDeal(request);
        } catch (Exception e) {
            resMap.put("fail", e.getMessage());
            return resMap;
        }

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

        // 参数验证
        Map<String, Object> paramMap;
        try {
            paramMap = paramsDeal(request);
        } catch (Exception e) {
            resMap.put("fail", e.getMessage());
            return resMap;
        }

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

        // 参数验证
        Map<String, Object> paramMap;
        try {
            paramMap = paramsDeal(request);
        } catch (Exception e) {
            resMap.put("fail", e.getMessage());
            return resMap;
        }

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

        // 参数验证
        Map<String, Object> paramMap;
        try {
            paramMap = paramsDeal(request);
        } catch (Exception e) {
            resMap.put("fail", e.getMessage());
            return resMap;
        }
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
    private Map<String, Object> paramsDeal(HttpServletRequest request) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        //获取团队id
        String teamId = request.getParameter("teamId");
        if (teamId == null) {
            throw new Exception("未找到当前公司");
        }
        paramMap.put("teamId", Integer.parseInt(teamId));
        //获取版本信息
        String productName = request.getParameter("productName");
        if (productName == null) {
            throw new Exception("产品信息未找到");
        }
        paramMap.put("productName", productName);
        // 购买类型
        String payType = request.getParameter("payType");
        if(!RsqSystemConstants.PAY_TYPE_LIST.contains(payType)) {
            throw new Exception("购买类型异常");
        }
        paramMap.put("payType",payType);

        //获取购买人数
        String userLimit = request.getParameter("userLimit");
        if (userLimit == null) {
            throw new Exception("购买人数未找到");
        }
        paramMap.put("userLimit", Integer.parseInt(userLimit));
        //获取购买天数
        String days = request.getParameter("days");
        if (days == null) {
            throw new Exception("购买天数未找到");
        }
        paramMap.put("days", Integer.parseInt(days));
        //购买费用
        String totalFee = request.getParameter("totalFee");
        if (totalFee == null) {
            throw new Exception("总价未找到");
        }
        paramMap.put("totalFee", totalFee);
        // 操作人员姓名记录
        String operator = request.getParameter("operator");
        if(operator == null) {
            throw new Exception("操作人员未找到");
        }
        paramMap.put("operator", operator);
        // 获取系统用户id
        User user = UserUtils.getUser();
        paramMap.put("manageId", user.getId());
        return paramMap;
    }
}
