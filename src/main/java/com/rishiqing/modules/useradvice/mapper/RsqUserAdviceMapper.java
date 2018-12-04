package com.rishiqing.modules.useradvice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.useradvice.entity.RsqUserAdvice;

import java.util.List;
import java.util.Map;

/**   
 * @Title: 用户意见mapper层
 * @Description: 用户意见mapper
 * @author rishiqing
 * @date 2018年11月12日15:34:17
 * @version V1.0
 *
 */
public interface RsqUserAdviceMapper extends BaseMapper<RsqUserAdvice> {

    List<RsqUserAdvice> ajaxList(Map map);

    int rsqUserAdviceCount(Map map);
}