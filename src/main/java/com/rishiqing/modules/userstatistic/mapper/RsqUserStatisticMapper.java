package com.rishiqing.modules.userstatistic.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rishiqing.modules.userstatistic.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**   
 * @Title: 日事清用户数据统计数据库控制层接口
 * @Description: 日事清用户数据统计数据库控制层接口
 * @author rishiqing
 * @date 2018-09-18 14:47:12
 * @version V1.0   
 *
 */
public interface RsqUserStatisticMapper extends BaseMapper<RsqUserStatistic> {

    /**
     * 用户数据统计（带分页信息）
     * @param var1
     * @param map
     * @return
     */
    List<RsqUserStatistic> ajaxList(RowBounds var1, Map map);

    /**
     * 获取全面的统计数据
     */
    List<RsqSystemStatistic> getRsqSystemStatistic();

    /**
     * 获取今天的统计数据
     */
    List<RsqTodayStatistic> getRsqTodayStatistic();

    /**
     * 获取昨天的统计数据
     */
    List<RsqYesterdayStatistic> getRsqYesterdayStatistic();

    /**
     * 获取某天的统计数据
     * @param map
     * @return
     */
    List<RsqDayStatistic> getRsqDayStatistic(Map map);


    /**
     * 账号激活
     * @return
     */
    int userActive(String userId);

    /**
     * 账号注销(冻结)
     */
    int userFreeze(String userId);
}