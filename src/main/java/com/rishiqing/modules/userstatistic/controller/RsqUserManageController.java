package com.rishiqing.modules.userstatistic.controller;

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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.rishiqing.core.common.controller.RsqBaseBeanController;
import com.rishiqing.modules.teammanage.service.IRsqTeamManageService;
import com.rishiqing.modules.userstatistic.entity.RsqSystemStatistic;
import com.rishiqing.modules.userstatistic.entity.RsqUserStatistic;
import com.rishiqing.modules.userstatistic.service.IRsqUserStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**   
 * @Title: 日事清用户数据统计
 * @Description: 日事清用户数据统计
 * @author rishiqing
 * @date 2018-09-18 14:47:12
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/userstatistic/rsqusermanage")
@RequiresPathPermission("userstatistic:rsqusermanage")
public class RsqUserManageController extends RsqBaseBeanController<RsqUserStatistic> {

    @Autowired
    protected IRsqUserStatisticService rsqUserStatisticService;

    @Autowired
    protected IRsqTeamManageService rsqTeamManageService;

    public RsqUserStatistic get(String id) {
        if (!ObjectUtils.isNullOrEmpty(id)) {
            return rsqUserStatisticService.selectById(id);
        } else {
            return newModel();
        }
    }

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

    @RequestMapping(value = "ajaxList", method = { RequestMethod.GET, RequestMethod.POST })
    @PageableDefaults(sort = "id=desc")
    private void ajaxList(Queryable queryable, PropertyPreFilterable propertyPreFilterable, HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        PageJson<RsqUserStatistic> pagejson = new PageJson<>(rsqUserStatisticService.ajaxList(queryable,request));
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        String content = JSON.toJSONString(pagejson, filter);

        StringUtils.printJson(response, content);
    }

    @RequestMapping(value = "{id}/userActive", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson userActive(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                ajaxJson.fail("激活失败,当前操作人没有权限");
                return ajaxJson;
            }
        } catch (Exception e) {
            ajaxJson.fail("激活失败,请联系管理员进行问题排查");
            return ajaxJson;
        }

        ajaxJson.success("激活成功");
        try {
            rsqUserStatisticService.userActive(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("激活失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "{id}/userFreeze", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson userFreeze(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                ajaxJson.fail("注销失败,当前操作人没有权限");
                return ajaxJson;
            }
        } catch (Exception e) {
            ajaxJson.fail("注销失败,请联系管理员进行问题排查");
            return ajaxJson;
        }

        ajaxJson.success("注销成功");
        try {
            rsqUserStatisticService.userFreeze(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("注销失败");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson updatePassword(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            //操作权限校验
            boolean flag = rsqTeamManageService.judgeUserPermission();
            if(!flag){
                ajaxJson.fail("修改密码失败,当前操作人没有权限");
                return ajaxJson;
            }
        } catch (Exception e) {
            ajaxJson.fail("修改密码失败,请联系管理员进行问题排查");
            return ajaxJson;
        }

        ajaxJson.success("修改密码成功");
        try {
//            rsqUserStatisticService.updateUserPassword(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("修改密码失败，请联系管理员进行问题排查");
        }
        return ajaxJson;
    }

    @RequestMapping(value = "bindingAccount", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson bindingAccount(@PathVariable("id") String id) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.success("删除成功");
        try {
//            rsqUserStatisticService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.fail("删除失败");
        }
        return ajaxJson;
    }
}
