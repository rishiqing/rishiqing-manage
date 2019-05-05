package com.rishiqing.modules.teammanage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rishiqing.modules.teammanage.entity.RsqPayOperator;
import com.rishiqing.modules.teammanage.entity.RsqPayOrder;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.teammanage.entity.RsqTeamStatus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**   
 * @Title: 团队管理数据库控制层接口
 * @Description: 团队管理数据库控制层接口
 * @author rishiqing
 * @date 2018-09-19 18:31:34
 * @version V1.0   
 *
 */
public interface RsqTeamManageMapper extends BaseMapper<RsqTeamManage> {

    List<RsqTeamManage> ajaxList(Map map);

    /** 统计数量 */
    int rsqTeamManageCount(Map map);

    /**
     * 通过id获取团队信息
     * @param id
     * @return
     */
    RsqTeamManage getRsqTeamManageById(String id);

    /**
     * 根据公司id获取公司状态，teamStatus
     * @param teamId
     * @return
     */
    @Deprecated
    RsqTeamStatus getRsqTeamStatusByTeamId(String teamId);

    /**
     * 通过公司 id 和 版本 id 获取公司状态信息
     * @param teamId 公司 id
     * @param teamVersionId 公司付费版本 id
     * @return
     * @author codingR
     * @date 2019/4/30 12:12
     */
    RsqTeamStatus getRsqTeamStatusByTeamIdAndTeamVersionId(@Param("teamId")Long teamId, @Param("teamVersionId") Long teamVersionId);

    /**
     * 通过 teamId 获取公司可用的状态信息列表
     * @param teamId 公司的 id
     * @return list 公司状态列表
     * @author codingR
     * @date 2019/5/5 14:41
     */
    List<RsqTeamStatus> listTeamStatusByTeamId (Long teamId) ;

    /**
     * 更新teamStatus信息
     * @param rsqTeamStatus
     */
    void updateTeamStatus(RsqTeamStatus rsqTeamStatus);

    /**
     * 插入teamStatus信息
     * @param rsqTeamStatus
     */
    void addTeamStatus(RsqTeamStatus rsqTeamStatus);

    /**
     * 添加充值记录
     * @param rsqPayOrder
     */
    void addPayOrder(RsqPayOrder rsqPayOrder);

    /**
     * 添加充值操作用户
     * @param rsqPayOperator
     */
    void addPayOperator(RsqPayOperator rsqPayOperator);
}