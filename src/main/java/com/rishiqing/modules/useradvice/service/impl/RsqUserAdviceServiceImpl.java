package com.rishiqing.modules.useradvice.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.core.util.CommonUtil;
import com.rishiqing.modules.useradvice.entity.RsqUserAdvice;
import com.rishiqing.modules.useradvice.mapper.RsqUserAdviceMapper;
import com.rishiqing.modules.useradvice.service.IRsqUserAdviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String[] paramsArr = new String[]{"username", "star", "dateCreated", "client"};
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
            resMap.put("createBeginDate", dateMap.get("first"));
            resMap.put("createEndDate", dateMap.get("second"));
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

}
