package com.rishiqing.modules.useradvice.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.core.util.CommonUtil;
import com.rishiqing.core.util.poi.ExportExcel;
import com.rishiqing.core.util.poi.template.RsqUserAdviceTemplate;
import com.rishiqing.modules.useradvice.entity.RsqUserAdvice;
import com.rishiqing.modules.useradvice.mapper.RsqUserAdviceMapper;
import com.rishiqing.modules.useradvice.service.IRsqUserAdviceService;
import freemarker.template.SimpleDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: 团队管理
 * @Description: 团队管理
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0
 *
 */
@Transactional
@Service("rsqUserAdviceService")
public class RsqUserAdviceServiceImpl extends CommonServiceImpl<RsqUserAdviceMapper,RsqUserAdvice> implements IRsqUserAdviceService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 将查询参数转换为mapper需要的参数
     * @param request
     * @return
     */
    @Override
    protected Map<String, Object> createConditionMap(HttpServletRequest request){
        return getEffectiveParams(super.createConditionMap(request));
    }

    /**
     * 获取有效参数
     * @param allMap
     * @return
     */
    private Map<String, Object> getEffectiveParams(Map<String, Object> allMap){
        //定义有效参数范围
        String[] paramsArr = new String[]{"email", "phoneNumber", "client", "star","type","dateCreated"};
        //定义返回值
        Map<String, Object> resMap = new HashMap<>();
        for(String tempParam : paramsArr){
            if(allMap.containsKey(tempParam)){
                resMap.put(tempParam, allMap.get(tempParam));
            }
        }

        //特殊参数处理，例如日期等
        if(resMap.get("dateCreated") != null){
            String createDate = (String) resMap.get("dateCreated");
            Map dateMap = CommonUtil.dateSplit(createDate);
            resMap.put("startDate", dateMap.get("first"));
            resMap.put("endDate", dateMap.get("second"));
        }

        if (resMap.get("client") != null) {
            if ("iOS".equals(resMap.get("client"))) {
                resMap.put("client","ios");
            }
            if ("android".equals(resMap.get("client"))) {
                resMap.put("client","androidDevice");
            }
        }

        if (resMap.get("type") != null) {
            if ("意见反馈".equals(resMap.get("type"))) {
                resMap.put("type","opinion");
            } else if ("解绑反馈".equals(resMap.get("type"))) {
                resMap.put("type","logout");
            } else if ("nps打分".equals(resMap.get("type"))){
                resMap.put("type","nps");
            }
        }

        return resMap;
    }

    @Override
    public Page<RsqUserAdvice> ajaxList(Queryable queryable, HttpServletRequest request) {
        //分页信息
        Pageable pageable = queryable.getPageable();
        com.baomidou.mybatisplus.plugins.Page<RsqUserAdvice> page =
                new com.baomidou.mybatisplus.plugins.Page<>(pageable.getPageNumber(), pageable.getPageSize());
        //参数信息
        Map<String, Object> map = createConditionMap(request);
        map.put("offset", page.getOffset());
        map.put("limit", page.getLimit());
        List<RsqUserAdvice> rsqUserAdviceList = this.baseMapper.ajaxList(map);

        page.setRecords(rsqUserAdviceList);

        //统计数量
        int count = this.baseMapper.rsqUserAdviceCount(map);
        return new PageImpl<>(page.getRecords(), queryable.getPageable(), count);
    }

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取需要导出的数据
        List<RsqUserAdvice> rsqUserAdviceList = this.baseMapper.export();
        // 装入导出 list
        List<RsqUserAdviceTemplate> templateList = new ArrayList<>();
        // 装入 excel
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (RsqUserAdvice advice : rsqUserAdviceList) {
            RsqUserAdviceTemplate temp = new RsqUserAdviceTemplate();
            temp.setUserId(String.valueOf(advice.getUserId()));
            temp.setEmail(advice.getEmail());
            temp.setPhoneNumber(advice.getPhoneNumber());
            temp.setClient(advice.getClient());
            temp.setVip(advice.getVip());
            temp.setStar(advice.getStar() != null ? String.valueOf(advice.getStar()) : "");
            temp.setType(advice.getType());
            temp.setRemark(advice.getRemark());
            temp.setDateCreated(format.format(advice.getDateCreated()));
            templateList.add(temp);
        }
        ExportExcel excel = new ExportExcel("", RsqUserAdviceTemplate.class);
        excel.setDataList(templateList);
        SimpleDateFormat ndf = new SimpleDateFormat("yyyyMMDDHHmmss");
        Date date = new Date();
        excel.write(response, "用户意见导出_"+ ndf.format(date) + ".xlsx");
        excel.dispose();
    }

}
