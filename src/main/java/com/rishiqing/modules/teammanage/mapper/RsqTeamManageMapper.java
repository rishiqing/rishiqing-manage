package com.rishiqing.modules.teammanage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rishiqing.modules.teammanage.entity.RsqPayOrder;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.teammanage.entity.RsqTeamStatus;
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

    List<RsqTeamManage> ajaxList(RowBounds var1, Map map);

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
    RsqTeamStatus getRsqTeamStatusByTeamId(String teamId);

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
}