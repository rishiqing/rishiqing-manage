package com.rishiqing.modules.payrecord.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rishiqing.modules.payrecord.entity.RsqPayRecord;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**   
 * @Title: 充值消费记录数据库控制层接口
 * @Description: 充值消费记录数据库控制层接口
 * @author rishiqing
 * @date 2018-09-20 10:29:50
 * @version V1.0   
 *
 */
public interface RsqPayRecordMapper extends BaseMapper<RsqPayRecord> {

    List<RsqPayRecord> ajaxList(RowBounds var1, Map map);
}