package com.rishiqing.modules.useradvice.controller;

import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.annotation.PageableDefaults;
import cn.jeeweb.core.query.data.PropertyPreFilterable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresMethodPermissions;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.core.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.rishiqing.core.common.controller.RsqBaseBeanController;
import com.rishiqing.modules.useradvice.entity.RsqUserAdvice;
import com.rishiqing.modules.useradvice.service.IRsqUserAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: 用户意见管理
 * @Description: 用户意见管理
 * @author rishiqing
 * @date 2018年11月12日16:19:38
 * @version V1.0
 *
 */
@Controller
@RequestMapping("${admin.url.prefix}/useradvice/useradvice")
@RequiresPathPermission("useradvice:useradvice")
public class UserAdviceController extends RsqBaseBeanController<RsqUserAdvice> {

    @Autowired
    protected IRsqUserAdviceService rsqUserAdviceService;

    @RequiresMethodPermissions("list")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
        return display("list");
    }

    /**
     * 获取用户意见列表
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
        PageJson<RsqUserAdvice> pagejson = new PageJson<>(rsqUserAdviceService.ajaxList(queryable,request));
        SerializeFilter filter = propertyPreFilterable.constructFilter(entityClass);
        String content = JSON.toJSONString(pagejson, filter);
        StringUtils.printJson(response, content);
    }

}
