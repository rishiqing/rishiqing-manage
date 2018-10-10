package com.rishiqing.modules.payrecord.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.utils.DateUtils;
import cn.jeeweb.modules.sys.service.IDictGroupService;
import com.rishiqing.core.util.CommonUtil;
import com.rishiqing.modules.payrecord.mapper.RsqPayRecordMapper;
import com.rishiqing.modules.payrecord.entity.RsqPayRecord;
import com.rishiqing.modules.payrecord.service.IRsqPayRecordService;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.userstatistic.entity.RsqUserStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**   
 * @Title: 充值消费记录
 * @Description: 充值消费记录
 * @author rishiqing
 * @date 2018-09-20 10:29:50
 * @version V1.0   
 *
 */
@Transactional
@Service("rsqPayRecordService")
public class RsqPayRecordServiceImpl  extends CommonServiceImpl<RsqPayRecordMapper,RsqPayRecord> implements  IRsqPayRecordService {

    @Autowired
    private IDictGroupService dictGroupService;

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
        String[] paramsArr = new String[]{"payDate", "payType", "plateform", "price", "teamName", "contacts"};
        //定义返回值
        Map<String, Object> resMap = new HashMap<>();
        for(String tempParam : paramsArr){
            if(allMap.containsKey(tempParam)){
                resMap.put(tempParam, allMap.get(tempParam));
            }
        }

        //特殊参数处理，例如日期等
        if(resMap.get("payDate") != null){
            String payDate = (String) resMap.get("payDate");
            Map dateMap = CommonUtil.dateSplit(payDate);
            resMap.put("payBeginDate", dateMap.get("first"));
            resMap.put("payEndDate", dateMap.get("second"));
        }
        return resMap;
    }

    @Override
    public Page<RsqPayRecord> ajaxList(Queryable queryable, HttpServletRequest request) {
        //分页信息
        Pageable pageable = queryable.getPageable();
        com.baomidou.mybatisplus.plugins.Page<RsqPayRecord> page =
                new com.baomidou.mybatisplus.plugins.Page<>(pageable.getPageNumber(), pageable.getPageSize());
        //参数信息
        Map<String, Object> map = createConditionMap(request);
        List<RsqPayRecord> rsqPayRecordList = this.baseMapper.ajaxList(page, map);

        //加工返回数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(RsqPayRecord temp : rsqPayRecordList){
            //充值时间
            Date payDate = temp.getPayDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(payDate);
            //充值天数
            String days = temp.getDays();
            //计算到期日期
            calendar.add(Calendar.DATE, Integer.parseInt(days));
            Date deadLine = calendar.getTime();
            temp.setDeadline(deadLine);
        }

        page.setRecords(rsqPayRecordList);
        return new PageImpl<>(page.getRecords(), queryable.getPageable(), page.getTotal());
    }
}
