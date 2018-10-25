package com.rishiqing.modules.teammanage.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.core.constant.RsqSystemConstants;
import com.rishiqing.core.util.CommonUtil;
import com.rishiqing.modules.common.entity.RsqPayProduct;
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
        map.put("offset", page.getOffset());
        map.put("limit", page.getLimit());
        List<RsqTeamManage> rsqTeamManageList = this.baseMapper.ajaxList(map);

        page.setRecords(rsqTeamManageList);

        //统计数量
        int count = this.baseMapper.rsqTeamManageCount(map);
        return new PageImpl<>(page.getRecords(), queryable.getPageable(), count);
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
     * 获取团队会员信息
     * @param rsqTeamManage
     * @return
     */
    @Override
    public Map<String, String> getTeamStaus(RsqTeamManage rsqTeamManage) {
        //定义返回值
        Map<String, String> resMap = new HashMap<>();
        if(rsqTeamManage == null){
            resMap.put("versionName", "不是会员");
            resMap.put("expired", "未创建公司");
            return resMap;
        }

        //获取teamId
        String teamId = rsqTeamManage.getId();
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);
        if(rsqTeamStatus == null){
            resMap.put("versionName", "不是会员");
            resMap.put("expired", "已创建公司");
            return resMap;
        }else{
            Integer teamVersionId = rsqTeamStatus.getTeamVersionId();
            RsqPayProduct payProduct = rsqCommonService.getRsqPayProductByTeamVersionId(teamVersionId);
            resMap.put("versionName", payProduct.getDescription());
            if(rsqTeamStatus.getExpired()){
                resMap.put("expired", "已失效");
            }else{
                resMap.put("expired", "未失效");
            }
            if(payProduct.getDescription().equals(RsqSystemConstants.BASE_PROFESSIONAL_NAME)){
                resMap.put("buyTypeRadio", "zy");
            }else if(payProduct.getDescription().equals(RsqSystemConstants.BASE_ENTERPRISE_NAME)){
                resMap.put("buyTypeRadio", "qy");
            }
        }
        return resMap;
    }

    /**
     * 开通试用
     * 逻辑：
     * 1、如果要开通的用户是企业版，则返回错误信息“企业版不能开通试用”
     * 2、如果要开通的用户是专业版，则只能开通“企业版”试用，如果开通的是专业版试用，则返回错误信息“当前用户已经是专业版，不能开通专业版试用！”
     * 专业版开通规则：将当前专业版的人数和剩余天数存放到表frozen_record中，然后修改teamStatus的记录信息
     * 3、如果要开通试用的用户不是会员，则可以选择开通企业版会员或专业版会员
     * 开通试用可以选择开通人数和天数
     *
     * 上述说明现在还是扯淡，等产品重新定需求（上面是旧版的逻辑，新版已经不再适用）
     * @param paramMap
     */
    @Transactional
    @Override
    public Map<String, String> rsqTry(Map<String, String> paramMap) {
        logger.debug("====开通试用start");
        Map<String, String> resMap = new HashMap<>();
        String teamId = paramMap.get("id");

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //判断用户会员是否到期
            if(rsqTeamStatus.getExpired()){
                //用户会员失效，初始化teamStatus
                rsqTeamStatus = resetTeamStatus(rsqTeamStatus);
                //更新teamStatus
                updateTeamStatus(rsqTeamStatus, paramMap);
            }else{
                resMap.put("fail", "开通试用失败！当前用户已经是会员，不支持开通试用，如有需求，请联系管理员处理!");
                return resMap;
            }
        }else{
            //teamStatus不存在，创建teamStatus
            rsqTeamStatus = addTeamStatus(paramMap);
        }

        paramMap.put("body", CommonUtil.getBodyForTrial(rsqTeamStatus.getUserLimit(), rsqTeamStatus.getDeadLine()));
        paramMap.put("payType", "pay");
        //添加充值记录
        addPayOrder(paramMap);
        logger.debug("====开通试用end");
        resMap.put("success", "开通适用成功！");
        return resMap;
    }

    /**
     * 购买
     * @param paramMap
     */
    @Transactional
    @Override
    public Map<String, String> rsqBuy(Map<String, String> paramMap) {
        //定义返回值
        Map<String, String> resMap = new HashMap<>();
        logger.debug("====购买start");
        String teamId = paramMap.get("id");

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);
        if(rsqTeamStatus != null){
            //判断用户会员是否到期
            if(rsqTeamStatus.getExpired()){
                //重置会员信息
                rsqTeamStatus = resetTeamStatus(rsqTeamStatus);
                //更新会员信息
                updateTeamStatus(rsqTeamStatus, paramMap);
            }else{
                resMap.put("fail", "购买失败！当前用户已经是会员，请选择续费、添加成员或版本升级！");
                return resMap;
            }
        }else{
            //teamStatus不存在，创建teamStatus
            rsqTeamStatus = addTeamStatus(paramMap);
        }

        paramMap.put("body", CommonUtil.getBodyForBase(rsqTeamStatus.getUserLimit(), rsqTeamStatus.getDeadLine()));
        paramMap.put("payType", "pay");
        //添加充值记录
        addPayOrder(paramMap);

        logger.debug("====购买end");
        return resMap;
    }

    /**
     * 续费
     * @param paramMap
     */
    @Transactional
    @Override
    public Map<String, String> rsqRenewal(Map<String, String> paramMap) {
        //定义返回值
        Map<String, String> resMap = new HashMap<>();

        logger.debug("====续费start");
        String teamId = paramMap.get("id");

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //判断用户会员是否到期
            if(rsqTeamStatus.getExpired()){
                resMap.put("fail", "续费失败！当前用户不是会员，请先开通会员！");
                return resMap;
            }else{
                //更新teamStatus
                updateTeamStatus(rsqTeamStatus, paramMap);
            }
        }else{
            //teamStatus不存在，返回提示，要求先给用户开通会员
            resMap.put("fail", "续费失败！当前用户不是会员，请先开通会员！");
            return resMap;
        }

        paramMap.put("payType", "renewal");
        paramMap.put("body", CommonUtil.getBodyForBase(rsqTeamStatus.getUserLimit(), rsqTeamStatus.getDeadLine()));
        //添加充值记录
        addPayOrder(paramMap);
        logger.debug("====续费end");
        resMap.put("success", "续费成功!");
        return resMap;
    }

    /**
     * 增加人数
     * @param paramMap
     */
    @Transactional
    @Override
    public Map<String, String> rsqAdd(Map<String, String> paramMap) {
        Map<String, String> resMap = new HashMap<>();
        logger.debug("====增加人数start");
        String teamId = paramMap.get("id");

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            //判断用户会员是否到期
            if(rsqTeamStatus.getExpired()){
                //teamStatus不存在，返回提示，要求先给用户开通会员
                resMap.put("fail", "添加成员失败！当前用户不是会员，请先开通会员！");
                return resMap;
            }else{
                //更新teamStatus
                updateTeamStatus(rsqTeamStatus, paramMap);
            }
        }else{
            //teamStatus不存在，返回提示，要求先给用户开通会员
            resMap.put("fail", "添加成员失败！当前用户不是会员，请先开通会员！");
            return resMap;
        }

        paramMap.put("payType", "add");
        paramMap.put("body", CommonUtil.getBodyForBase(rsqTeamStatus.getUserLimit(), rsqTeamStatus.getDeadLine()));
        //添加充值记录
        addPayOrder(paramMap);

        logger.debug("====增加成员end");

        resMap.put("success", "添加成员成功！");
        return resMap;
    }

    /**
     * 版本更新
     * @param paramMap
     */
    @Transactional
    @Override
    public Map<String, String> rsqUpdate(Map<String, String> paramMap) {
        Map<String, String> resMap = new HashMap<>();
        logger.debug("====版本更新start");
        String teamId = paramMap.get("id");

        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamId(teamId);

        if(rsqTeamStatus != null){
            ///判断用户会员是否到期
            if(rsqTeamStatus.getExpired()){
                //teamStatus不存在，返回提示，要求先给用户开通会员
                resMap.put("fail", "版本升级失败！当前用户不是会员，请先开通会员！");
                return resMap;
            }else{
                //更新teamStatus
                updateTeamStatus(rsqTeamStatus, paramMap);
            }
        }else{
            //teamStatus不存在，返回提示，要求先给用户开通会员
            resMap.put("fail", "版本升级失败！当前用户不是会员，请先开通会员！");
            return resMap;
        }

        paramMap.put("payType", "update");
        paramMap.put("body", CommonUtil.getBodyForBase(rsqTeamStatus.getUserLimit(), rsqTeamStatus.getDeadLine()));
        //添加充值记录
        addPayOrder(paramMap);

        logger.debug("====版本更新end");

        resMap.put("success", "版本升级成功！");
        return resMap;
    }

    /**
     * 初始化充值记录信息
     * @param rsqTeamStatus
     * @return
     */
    private RsqTeamStatus resetTeamStatus(RsqTeamStatus rsqTeamStatus){
        Date today = new Date();
        //最后更新时间修改为现在
        rsqTeamStatus.setLastUpdated(today);

        //会员到期时间修改为今天
        today = CommonUtil.delHHMMSS(today);
        rsqTeamStatus.setDeadLine(today);

        //人数修改为0
        rsqTeamStatus.setUserLimit(0);
        return rsqTeamStatus;
    }


    /**
     * 当前用户操作权限判断
     * @return
     */
    @Override
    public boolean judgeUserPermission() {
        return true;
//        return rsqCommonService.judgeUserPermission();
    }

    /**
     * 添加充值记录
     * @param paramMap
     */
    private void addPayOrder(Map<String, String> paramMap){
        Date date = new Date();
        //创建充值记录对象
        RsqPayOrder rsqPayOrder = new RsqPayOrder();

        /** version */
        rsqPayOrder.setVersion(0);

        /** dateCreated */
        rsqPayOrder.setDateCreated(date);

        /** lastUpdated */
        rsqPayOrder.setLastUpdated(date);

        /**body订单说明*/
        rsqPayOrder.setBody(paramMap.get("body"));

        /** plateform设置充值平台 */
        rsqPayOrder.setPlatform("系统");

        /** days 购买天数*/
        if(paramMap.get("buyDays") != null){
            rsqPayOrder.setDays(Integer.parseInt(paramMap.get("buyDays")));
        }else{
            rsqPayOrder.setDays(0);
        }

        /** teamid */
        rsqPayOrder.setTeamId(Integer.parseInt(paramMap.get("id")));

        /** payProductId 购买的产品，续费，加人，不填此字段*/
        if(paramMap.get("payProductId") != null){
            rsqPayOrder.setPayProductId(Integer.parseInt(paramMap.get("payProductId")));
        }else {
            //获取购买的版本
            Integer teamVersionId = null;
            if(paramMap.get("teamVersionId") == null){
                String version = paramMap.get("buyVersion");
                if("zy".equals(version)){
                    teamVersionId = rsqCommonService.getBaseProfessionalVerionId();
                }else if("qy".equals(version)){
                    teamVersionId = rsqCommonService.getBaseEnterpriseVersionId();
                }
            } else {
                teamVersionId = Integer.parseInt(paramMap.get("teamVersionId"));
            }
            RsqPayProduct rsqPayProduct = rsqCommonService.getRsqPayProductByTeamVersionId(teamVersionId);
            rsqPayOrder.setPayProductId(rsqPayProduct.getId());
        }

        /** totalFee 订单总价*/
        if(paramMap.get("totalFee") != null){
            rsqPayOrder.setTotalFee(Double.parseDouble(paramMap.get("totalFee")));
        }else{
            rsqPayOrder.setTotalFee(0.00);
        }

        /** userLimit 购买人数：升级，续费，不填写此字段*/
        if(paramMap.get("userLimit") != null){
            rsqPayOrder.setUserLimit(Integer.parseInt(paramMap.get("userLimit")));
        }else{
            rsqPayOrder.setUserLimit(10);
        }

        /** userId 创建人id */
        RsqUser rsqUser = rsqCommonService.getUserInfoInRishiqingDB();
        rsqPayOrder.setUserId(rsqUser.getId());

        /** client */
        rsqPayOrder.setClient("sys");

        /** status */
        rsqPayOrder.setStatus(true);

        /**
         * payType 支付类型，目前支持
         * pay
         * add
         * renewals
         * update
         */
        if(paramMap.get("payType") != null){
            rsqPayOrder.setPayType(paramMap.get("payType"));
        }

        /** 获取订单号 */
        String outTradeNo = CommonUtil.getOutTradeNo();

        /** transactNo内部交易号 */
        rsqPayOrder.setTransactNo(outTradeNo);

        /** outTradeNo订单号*/
        rsqPayOrder.setOutTradeNo(outTradeNo);

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
    private RsqTeamStatus addTeamStatus(Map<String, String> paramMap){
        Date date = new Date();
        date = CommonUtil.delHHMMSS(date);

        RsqTeamStatus rsqTeamStatus = new RsqTeamStatus();
        //1、固定值0
        rsqTeamStatus.setVersion(0);

        //2、会员版本
        String version = paramMap.get("buyVersion");
        if("zy".equals(version)){
            Integer zyId = rsqCommonService.getBaseProfessionalVerionId();
            rsqTeamStatus.setTeamVersionId(zyId);
            paramMap.put("teamVersionId", zyId + "");
        }else if("qy".equals(version)){
            Integer qyId = rsqCommonService.getBaseEnterpriseVersionId();
            rsqTeamStatus.setTeamVersionId(qyId);
            paramMap.put("teamVersionId", qyId + "");
        }else{
            Integer zyId = rsqCommonService.getBaseProfessionalVerionId();
            rsqTeamStatus.setTeamVersionId(zyId);
            paramMap.put("teamVersionId", zyId + "");
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
            rsqTeamStatus.setUserLimit(0);
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
        return rsqTeamStatus;
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
            paramMap.put("teamVersionId", zyId + "");
        }else if("qy".equals(version)){
            Integer qyId = rsqCommonService.getBaseEnterpriseVersionId();
            rsqTeamStatus.setTeamVersionId(qyId);
            paramMap.put("teamVersionId", qyId + "");
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
