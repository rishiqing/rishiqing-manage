package com.rishiqing.modules.userstatistic.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.query.data.Condition;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.modules.userstatistic.entity.RsqSystemStatistic;
import com.rishiqing.modules.userstatistic.entity.RsqUserStatistic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**   
 * @Title: 日事清用户数据统计
 * @Description: 日事清用户数据统计
 * @author rishiqing
 * @date 2018-09-18 14:47:12
 * @version V1.0   
 *
 */
public interface IRsqUserStatisticService extends ICommonService<RsqUserStatistic> {

    Page<RsqUserStatistic> ajaxList(Queryable queryable, HttpServletRequest request);

    /**
     * 获取首页需要的统计数据
     * @return
     */
    RsqSystemStatistic getMainPageInfo();
}

