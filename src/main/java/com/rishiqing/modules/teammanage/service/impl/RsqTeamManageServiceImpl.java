package com.rishiqing.modules.teammanage.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.core.util.CommonUtil;
import com.rishiqing.modules.common.entity.RsqUser;
import com.rishiqing.modules.common.service.IRsqCommonService;
import com.rishiqing.modules.teammanage.entity.RsqPayOrder;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.teammanage.entity.RsqTeamStatus;
import com.rishiqing.modules.teammanage.mapper.RsqTeamManageMapper;
import com.rishiqing.modules.teammanage.service.IRsqTeamManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
@Service("rsqTeamManageService")
public class RsqTeamManageServiceImpl  extends CommonServiceImpl<RsqTeamManageMapper,RsqTeamManage> implements  IRsqTeamManageService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRsqCommonService rsqCommonService;

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
        String[] paramsArr = new String[]{"teamName", "teamMember", "createDate"};
        //定义返回值
        Map<String, Object> resMap = new HashMap<>();
        for(String tempParam : paramsArr){
            if(allMap.containsKey(tempParam)){
                resMap.put(tempParam, allMap.get(tempParam));
            }
        }

        //特殊参数处理，例如日期等
        if(resMap.get("createDate") != null){
            String createDate = (String) resMap.get("createDate");
            Map dateMap = CommonUtil.dateSplit(createDate);
            resMap.put("createBeginDate", dateMap.get("first"));
            resMap.put("createEndDate", dateMap.get("second"));
        }
        return resMap;
    }

    @Override
    public Page<RsqTeamManage> ajaxList(Queryable queryable, HttpServletRequest request) {
        //分页信息
        Pageable pageable = queryable.getPageable();
        com.baomidou.mybatisplus.plugins.Page<RsqTeamManage> page =
                new com.baomidou.mybatisplus.plugins.Page<>(pageable.getPageNumber(), pageable.getPageSize());
        //参数信息
        Map<String, Object> map = createConditionMap(request);
        List<RsqTeamManage> rsqTeamManageList = this.baseMapper.ajaxList(page, map);

        page.setRecords(rsqTeamManageList);
        return new PageImpl<>(page.getRecords(), queryable.getPageable(), page.getTotal());
    }

    /**
     * 通过id获取team信息
     * @param id
     * @return
     */
    @Override
    public RsqTeamManage getRsqTeamManageById(String id) {
        return this.baseMapper.getRsqTeamManageById(id);
    }

    /**
     * 开通试用
     * @param paramMap
     */
    @Override
    public void rsqTry(Map<String, String> paramMap) {
        logger.debug("====开通试用start");
        String teamId = paramMap.get("id");

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //teamstatus存在，更新teamStatus
            updateTeamStatus(rsqTeamStatus, paramMap);
        }else{
            //teamStatus不存在，创建teamStatus
            addTeamStatus(paramMap);
        }

        //添加充值记录
        /**
         * 支付类型，目前支持
         * pay
         * add
         * renewals
         * update
         */
        paramMap.put("body", "开通续费");
        addPayOrder(paramMap);

        logger.debug("====开通试用end");
    }

    /**
     * 购买
     * @param paramMap
     */
    @Override
    public void rsqBuy(Map<String, String> paramMap) {
        logger.debug("====购买start");
        String teamId = paramMap.get("id");

        //添加充值记录
        addPayOrder(paramMap);

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //teamstatus存在，更新teamStatus
            updateTeamStatus(rsqTeamStatus, paramMap);
        }else{
            //teamStatus不存在，创建teamStatus
            addTeamStatus(paramMap);
        }

        logger.debug("====购买end");
    }

    /**
     * 续费
     * @param paramMap
     */
    @Override
    public void rsqRenewal(Map<String, String> paramMap) {
        logger.debug("====续费start");
        String teamId = paramMap.get("id");

        //添加充值记录
        addPayOrder(paramMap);

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //teamStatus存在，更新teamStatus
            updateTeamStatus(rsqTeamStatus, paramMap);
        }else{
            //teamStatus不存在，创建teamStatus
            addTeamStatus(paramMap);
        }
        logger.debug("====续费end");
    }

    /**
     * 增加人数
     * @param paramMap
     */
    @Override
    public void rsqAdd(Map<String, String> paramMap) {
        logger.debug("====增加人数start");
        String teamId = paramMap.get("id");

        //添加充值记录
        addPayOrder(paramMap);

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //teamStatus存在，更新teamStatus
            updateTeamStatus(rsqTeamStatus, paramMap);
        }else{
            //teamStatus不存在，创建teamStatus
            addTeamStatus(paramMap);
        }
        logger.debug("====增加人数end");
    }

    /**
     * 版本更新
     * @param paramMap
     */
    @Override
    public void rsqUpdate(Map<String, String> paramMap) {
        logger.debug("====版本更新start");
        String teamId = paramMap.get("id");

        //添加充值记录
        addPayOrder(paramMap);

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //teamStatus存在，更新teamStatus
            updateTeamStatus(rsqTeamStatus,paramMap);
        }else{
            //teamStatus不存在，创建teamStatus
            addTeamStatus(paramMap);
        }
        logger.debug("====版本更新end");
    }

    /**
     * 当前用户操作权限判断
     * @return
     */
    @Override
    public boolean judgeUserPermission() {
        return rsqCommonService.judgeUserPermission();
    }

    /**
     * 添加充值记录
     * @param paramMap
     */
    private void addPayOrder(Map<String, String> paramMap){
        Date date = new Date();
        //创建充值记录对象
        RsqPayOrder rsqPayOrder = new RsqPayOrder();
        /**订单说明*/
        rsqPayOrder.setBody(paramMap.get("body"));
        /**购买天数*/
        if(paramMap.get("days") != null){
            rsqPayOrder.setDays(Integer.parseInt("days"));
        }else{
            rsqPayOrder.setDays(0);
        }
        /** teamid */
        rsqPayOrder.setTeamId(Integer.parseInt(paramMap.get("id")));

//        /**购买的产品，续费，加人，不填此字段*/
//        if(paramMap.get("payProductId") != null){
//            rsqPayOrder.setPayProductId(Integer.parseInt("payProductId"));
//        }else {
//            String version = paramMap.get("buyVersion");
//            if("zy".equals(version)){
//                rsqTeamStatus.setTeamVersionId(13);
//            }else if("qy".equals(version)){
//                rsqTeamStatus.setTeamVersionId(16);
//            }
//            Integer baseId = rsqCommonService.getBaseProfessionalProductId();
//            rsqPayOrder.setPayProductId(baseId);
//        }

        /**订单总价*/
        if(paramMap.get("totalFee") != null){
            rsqPayOrder.setTotalFee(Double.parseDouble(paramMap.get("totalFee")));
        }else{
            rsqPayOrder.setTotalFee(0.00);
        }

        /**购买人数：升级，续费，不填写此字段*/
        if(paramMap.get("userLimit") != null){
            rsqPayOrder.setUserLimit(Integer.parseInt("userLimit"));
        }else{
            rsqPayOrder.setUserLimit(10);
        }

//        /** 创建人id */
//        RsqUser rsqUser = rsqCommonService.getUserInfoInRishiqingDB();
//        rsqPayOrder.setUserId(rsqUser.getId());

        /**
         * 支付类型，目前支持
         * pay
         * add
         * renewals
         * update
         */
        if(paramMap.get("payType") != null){
            rsqPayOrder.setPayType(paramMap.get("payType"));
        }

        String time = date.getTime() + "";
        /** 内部交易号 */
        rsqPayOrder.setTransactNo(time);
        /**订单号*/
        rsqPayOrder.setOutTradeNo(time);

        addPayOrder(rsqPayOrder);
    }

    /**
     * 添加充值记录
     * @param rsqPayOrder
     */
    private void addPayOrder(RsqPayOrder rsqPayOrder){
        //插入数据库
        this.baseMapper.addPayOrder(rsqPayOrder);
    }

    /**
     * 添加teamStatus
     * @param paramMap
     */
    private void addTeamStatus(Map<String, String> paramMap){
        Date date = new Date();

        RsqTeamStatus rsqTeamStatus = new RsqTeamStatus();
        //1、固定值0
        rsqTeamStatus.setVersion(0);

        //2、会员版本
        String version = paramMap.get("buyVersion");
        if("zy".equals(version)){
            rsqTeamStatus.setTeamVersionId(rsqCommonService.getBaseProfessionalVerionId());
        }else if("qy".equals(version)){
            rsqTeamStatus.setTeamVersionId(rsqCommonService.getBaseEnterpriseVersionId());
        }else{
            //没有传值，默认开通专业版的了
            rsqTeamStatus.setTeamVersionId(rsqCommonService.getBaseProfessionalVerionId());
        }

        //3、是否过期,新创建的默认都是未过期的
        rsqTeamStatus.setExpired(false);

        //4、创建时间
        rsqTeamStatus.setDateCreated(date);

        //5、更新时间
        rsqTeamStatus.setLastUpdated(date);

        //6、人数
        String addMember = paramMap.get("buyNumbers");
        if(addMember != null && !"".equals(addMember)){
            rsqTeamStatus.setUserLimit(Integer.parseInt(addMember));
        }else{
            rsqTeamStatus.setUserLimit(10);
        }

        //7、公司
        rsqTeamStatus.setTeamId(Integer.parseInt(paramMap.get("id")));

        //8、到期时间
        String addDay = paramMap.get("buyDays");
        if(addDay != null && !"".equals(addDay)){
            rsqTeamStatus.setDeadLine(CommonUtil.addDays(date, Integer.parseInt(addDay)));
        }else{
            rsqTeamStatus.setDeadLine(CommonUtil.addDays(date, 1));
        }

        addTeamStatus(rsqTeamStatus);
    }

    /**
     * 添加teamStatus
     */
    private void addTeamStatus(RsqTeamStatus rsqTeamStatus){
        //插入数据库
        this.baseMapper.addTeamStatus(rsqTeamStatus);
    }

    /**
     * 更新teamStatus
     */
    private void updateTeamStatus(RsqTeamStatus rsqTeamStatus,Map<String, String> paramMap){
        //1、人数更新
        String addMember = paramMap.get("buyNumbers");
        if(addMember != null && !"".equals(addMember)){
            rsqTeamStatus.setUserLimit(rsqTeamStatus.getUserLimit() + Integer.parseInt(addMember));
        }
        //2、天数更新
        String addDay = paramMap.get("buyDays");
        if(addDay != null && !"".equals(addDay)){
            //当前到期日期
            Date deadLine = rsqTeamStatus.getDeadLine();
            rsqTeamStatus.setDeadLine(CommonUtil.addDays(deadLine, Integer.parseInt(addDay)));
        }
        //3、版本更新
        String version = paramMap.get("buyVersion");
        if("zy".equals(version)){
            Integer zyId = rsqCommonService.getBaseProfessionalVerionId();
            rsqTeamStatus.setTeamVersionId(zyId);
        }else if("qy".equals(version)){
            Integer qyId = rsqCommonService.getBaseEnterpriseVersionId();
            rsqTeamStatus.setTeamVersionId(qyId);
        }
        //4、状态更新
        rsqTeamStatus.setExpired(false);
        //5、更新日期
        rsqTeamStatus.setLastUpdated(new Date());
        updateTeamStatus(rsqTeamStatus);
    }

    /**
     * 更新teamStatus
     */
    private void updateTeamStatus(RsqTeamStatus rsqTeamStatus){
        //更新teamStatus
        this.baseMapper.updateTeamStatus(rsqTeamStatus);
    }
}
