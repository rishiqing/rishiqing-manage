package com.rishiqing.modules.userstatistic.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.*;
import cn.jeeweb.core.query.parse.QueryToWrapper;
import cn.jeeweb.core.utils.DateUtils;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rishiqing.core.util.CommonUtil;
import com.rishiqing.modules.userstatistic.entity.*;
import com.rishiqing.modules.userstatistic.mapper.RsqUserStatisticMapper;
import com.rishiqing.modules.userstatistic.service.IRsqUserStatisticService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**   
 * @Title: 日事清用户数据统计
 * @Description: 日事清用户数据统计
 * @author rishiqing
 * @date 2018-09-18 14:47:12
 * @version V1.0   
 *
 */
@Transactional
@Service("rsqUserStatisticService")
public class RsqUserStatisticServiceImpl extends CommonServiceImpl<RsqUserStatisticMapper,RsqUserStatistic> implements  IRsqUserStatisticService {

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
        String[] paramsArr = new String[]{"name", "email", "phone", "registDate", "lastLoginDate"};
        //定义返回值
        Map<String, Object> resMap = new HashMap<>();
        for(String tempParam : paramsArr){
            if(allMap.containsKey(tempParam)){
                resMap.put(tempParam, allMap.get(tempParam));
            }
        }

        //特殊参数处理，例如日期等
        if(resMap.get("registDate") != null){
            String registDateStr = (String) resMap.get("registDate");
            Map dateMap = CommonUtil.dateSplit(registDateStr);
            resMap.put("registBeginDate", dateMap.get("first"));
            resMap.put("registEndDate", dateMap.get("second"));
        }
        if(resMap.get("lastLoginDate") != null){
            String lastLoginDateStr = (String) resMap.get("lastLoginDate");
            Map dateMap = CommonUtil.dateSplit(lastLoginDateStr);
            resMap.put("lastLoginBeginDate", dateMap.get("first"));
            resMap.put("lastLoginEndDate", dateMap.get("second"));
        }
        return resMap;
    }

    /**
     * 获取列表（带分页信息）
     * @param queryable
     * @param request
     * @return
     */
    @Override
    public Page<RsqUserStatistic> ajaxList(Queryable queryable, HttpServletRequest request) {
        //分页信息
        Pageable pageable = queryable.getPageable();
        com.baomidou.mybatisplus.plugins.Page<RsqUserStatistic> page =
                new com.baomidou.mybatisplus.plugins.Page<>(pageable.getPageNumber(), pageable.getPageSize());
        //参数信息
        Map<String, Object> map = createConditionMap(request);
        List<RsqUserStatistic> rsqUserStatisticList = this.baseMapper.ajaxList(page, map);

        //返回数据加工
        for(RsqUserStatistic temp : rsqUserStatisticList){
            Date registDate = temp.getRegistDate();
            double day = DateUtils.getDistanceOfTwoDate(registDate, new Date());
            temp.setRegistDay((int)day + "天");
        }
        page.setRecords(rsqUserStatisticList);
        return new PageImpl<>(page.getRecords(), queryable.getPageable(), page.getTotal());
    }

    @Override
    public RsqSystemStatistic getMainPageInfo() {
        //定义返回值
        RsqSystemStatistic rsqSystemStatistic = new RsqSystemStatistic();
        //1、获取全面的统计数据
        List<RsqSystemStatistic> rsqSystemStatisticList = this.baseMapper.getRsqSystemStatistic();
        if(rsqSystemStatisticList != null && rsqSystemStatisticList.size() > 0){
            rsqSystemStatistic = rsqSystemStatisticList.get(0);
        }

        //2、获取今天的统计数据
        Map<String, Object> map = new HashMap<>();
        Date queryDate = new Date();
        map.put("queryDate", queryDate);
        List<RsqDayStatistic> rsqTodayStatisticList = this.baseMapper.getRsqDayStatistic(map);
        if(rsqTodayStatisticList != null && rsqTodayStatisticList.size() > 0){
            rsqSystemStatistic.setTodayStatistic(rsqTodayStatisticList.get(0));
        }

        //3、获取昨天的统计数据
        List<RsqDayStatistic> rsqYesterdayStatisticList = this.baseMapper.getRsqDayStatistic(map);
        if(rsqYesterdayStatisticList != null && rsqYesterdayStatisticList.size() > 0){
            rsqSystemStatistic.setYesterdayStatistic(rsqYesterdayStatisticList.get(0));
        }

        //钉钉统计暂时不计入统计
        return rsqSystemStatistic;
    }
}
