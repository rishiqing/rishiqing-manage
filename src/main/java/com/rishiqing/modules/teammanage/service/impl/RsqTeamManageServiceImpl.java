package com.rishiqing.modules.teammanage.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.core.constant.RsqSystemConstants;
import com.rishiqing.core.util.CommonUtil;
import com.rishiqing.modules.common.entity.RsqPayProduct;
import com.rishiqing.modules.common.entity.RsqTeamVersion;
import com.rishiqing.modules.common.entity.RsqUser;
import com.rishiqing.modules.common.service.IRsqCommonService;
import com.rishiqing.modules.teammanage.entity.RsqPayOperator;
import com.rishiqing.modules.teammanage.entity.RsqPayOrder;
import com.rishiqing.modules.teammanage.entity.RsqTeamManage;
import com.rishiqing.modules.teammanage.entity.RsqTeamStatus;
import com.rishiqing.modules.teammanage.mapper.RsqTeamManageMapper;
import com.rishiqing.modules.teammanage.service.IRsqTeamManageService;
import freemarker.template.SimpleDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        String[] paramsArr = new String[]{"teamName", "teamMember", "createDate", "linkedPhone"};
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


    @Override
    public List<Map<String, String>> listTeamStatus(RsqTeamManage rsqTeamManage) {
        List<Map<String, String>> results = new ArrayList<>();
        // 公司详情未空，则直接返回
        if (rsqTeamManage == null) {
            Map<String, String> resMap = new HashMap<>();
            resMap.put("versionName", "不是会员");
            resMap.put("expired", "未创建公司");
            results.add(resMap);
        }

        // 通过公司 id 获取公司在启用的版本信息
        List<RsqTeamStatus> teamStatusList = baseMapper.listTeamStatusByTeamId(Long.parseLong(rsqTeamManage.getId()));
        // 如果公司没有可用的状态信息，说明要么过期，要么没有执行过充值
        if (teamStatusList.size() == 0) {
            Map<String, String> resMap = new HashMap<>();
            resMap.put("versionName", "不是会员");
            resMap.put("expired", "已创建公司");
            results.add(resMap);
        } else {
            // 获取所有的 rsqTeamVersion ，用来做对比
            Map<Integer, RsqTeamVersion> idToTeamVersionMap = new HashMap<>();
            List<RsqTeamVersion> teamVersionList = rsqCommonService.listTeamVersion();
            for (RsqTeamVersion it : teamVersionList) {
                idToTeamVersionMap.put(it.getId(), it);
            }
            // 获取所有可以购买的产品
            List<RsqPayProduct> payProductList = rsqCommonService.listRsqPayProduct();
            Map<Integer, RsqPayProduct> teamVersionIdToPayProductMap = new HashMap<>();
            for (RsqPayProduct it : payProductList) {
                teamVersionIdToPayProductMap.put(it.getTeamVersionId(), it);
            }
            // 遍历公司状态列表
            RsqTeamVersion v;
            RsqPayProduct p;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (RsqTeamStatus status : teamStatusList) {
                Map<String, String> resMap = new HashMap<>();
                v = idToTeamVersionMap.get(status.getTeamVersionId());
                p = teamVersionIdToPayProductMap.get(status.getTeamVersionId());
                if(RsqSystemConstants.TEAM_VERSION_TRIAL_ENTERPRISE.equals(v.getType())){
                    resMap.put("level","1");
                } else if (RsqSystemConstants.TEAM_VERSION_TRIAL_PROFESSIONAL.equals(v.getType())) {
                    resMap.put("level","2");
                } else if (RsqSystemConstants.TEAM_VERSION_ADVANCED_ENTERPRISE.equals(v.getType())) {
                    resMap.put("level","3");
                } else if (RsqSystemConstants.TEAM_VERSION_BASE_ENTERPRISE.equals(v.getType())) {
                    resMap.put("level","4");
                } else if (RsqSystemConstants.TEAM_VERSION_ADVANCED_PROFESSIONAL.equals(v.getType())) {
                    resMap.put("level","5");
                } else if (RsqSystemConstants.TEAM_VERSION_BASE_PROFESSIONAL.equals(v.getType())) {
                    resMap.put("level","6");
                } else {
                    continue;
                }
                resMap.put("deadLine",sdf.format(status.getDeadLine()));
                resMap.put("versionName",p.getDescription());
                resMap.put("expired", "未失效");
                resMap.put("productName",v.getType());
                results.add(resMap);
            }
            // 执行一次排序，通过 level 进行排序
            Collections.sort(results, new Comparator<Map<String, String>>() {
                @Override
                public int compare(Map<String, String> o1, Map<String, String> o2) {
                    return o1.get("level").compareTo(o2.get("level"));
                }
            });
            // 重新遍历
            boolean first = true;
            for (Map<String, String> map : results) {
                if (first) {
                    map.put("priority","启用");
                    first = false;
                } else {
                    map.put("priority","停用");
                }
            }
        }
        return results;
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
        // 通过公司 id 获取公司在启用的版本信息
        List<RsqTeamStatus> teamStatusList = baseMapper.listTeamStatusByTeamId(Long.parseLong(rsqTeamManage.getId()));
        // 如果公司没有可用的状态信息，说明要么过期，要么没有执行过充值
        if (teamStatusList.size() == 0) {
            resMap.put("versionName", "不是会员");
            resMap.put("expired", "已创建公司");
            return resMap;
        } else {
            // 获取所有的 rsqTeamVersion ，用来做对比
            Map<String ,Integer> typeToIdMap = new HashMap<>();
            Map<Integer, RsqTeamVersion> idToTeamVersionMap = new HashMap<>();
            List<RsqTeamVersion> teamVersionList = rsqCommonService.listTeamVersion();
            for (RsqTeamVersion it : teamVersionList) {
                typeToIdMap.put(it.getType(), it.getId());
                idToTeamVersionMap.put(it.getId(), it);
            }

            Map<Integer, RsqTeamStatus> teamVersionIdToTeamStatusMap = new HashMap<>();
            for (RsqTeamStatus it : teamStatusList) {
                teamVersionIdToTeamStatusMap.put(it.getTeamVersionId(), it);
            }

            // 优先级遍历
            RsqTeamStatus defaultTeamStatus;
            if (teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_TRIAL_ENTERPRISE)) != null) {
                defaultTeamStatus = teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_TRIAL_ENTERPRISE));
            } else if (teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_TRIAL_PROFESSIONAL)) != null) {
                defaultTeamStatus = teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_TRIAL_PROFESSIONAL));
            } else if (teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_ADVANCED_ENTERPRISE)) != null) {
                defaultTeamStatus = teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_ADVANCED_ENTERPRISE));
            } else if (teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_BASE_ENTERPRISE)) != null) {
                defaultTeamStatus = teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_BASE_ENTERPRISE));
            } else if (teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_ADVANCED_PROFESSIONAL)) != null) {
                defaultTeamStatus = teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_ADVANCED_PROFESSIONAL));
            } else if (teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_BASE_PROFESSIONAL)) != null) {
                defaultTeamStatus = teamVersionIdToTeamStatusMap.get(typeToIdMap.get(RsqSystemConstants.TEAM_VERSION_BASE_PROFESSIONAL));
            } else {
                defaultTeamStatus = null;
            }
            RsqPayProduct payProduct = rsqCommonService.getRsqPayProductByTeamVersionId(defaultTeamStatus.getTeamVersionId());
            RsqTeamVersion teamVersion = idToTeamVersionMap.get(defaultTeamStatus.getTeamVersionId());
            resMap.put("versionName", payProduct.getDescription());
            resMap.put("expired", "未失效");
            resMap.put("productName",teamVersion.getType());
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
    public Map<String, String> rsqTry(Map<String, Object> paramMap) {
        logger.debug("====开通试用start");
        Map<String, String> resMap = new HashMap<>();
        Integer teamId = (Integer) paramMap.get("teamId");
        String productName = (String) paramMap.get("productName");

        // 获取公司版本信息
        RsqTeamVersion rsqTeamVersion = rsqCommonService.getTeamVersionByType(productName);
        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamIdAndTeamVersionId(
                Long.parseLong(String.valueOf(teamId)),
                Long.parseLong(String.valueOf(rsqTeamVersion.getId()))
        );

        if(rsqTeamStatus != null){
            //判断用户会员是否到期
            if(rsqTeamStatus.getExpired()){
                //用户会员失效，初始化teamStatus
                resetTeamStatus(rsqTeamStatus);
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
        //添加充值记录
        RsqPayOrder rsqPayOrder = addPayOrder(rsqTeamStatus , paramMap);
        // 添加充值人员操作记录
        RsqPayOperator rsqPayOperator = addPayOperator(rsqPayOrder, paramMap);
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
    public Map<String, String> rsqBuy(Map<String, Object> paramMap) {
        //定义返回值
        Map<String, String> resMap = new HashMap<>();
        logger.debug("====购买start");
        Integer teamId = (Integer) paramMap.get("teamId");
        String productName = (String) paramMap.get("productName");

        // 获取公司版本信息
        RsqTeamVersion rsqTeamVersion = rsqCommonService.getTeamVersionByType(productName);
        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamIdAndTeamVersionId(
                Long.parseLong(String.valueOf(teamId)),
                Long.parseLong(String.valueOf(rsqTeamVersion.getId()))
        );


        if(rsqTeamStatus != null){
            //判断用户会员是否到期
            if(rsqTeamStatus.getExpired()){
                //重置会员信息
                resetTeamStatus(rsqTeamStatus);
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
        //添加充值记录
        RsqPayOrder rsqPayOrder = addPayOrder(rsqTeamStatus, paramMap);
        // 添加充值人员操作记录
        RsqPayOperator rsqPayOperator = addPayOperator(rsqPayOrder, paramMap);
        logger.debug("====购买end");
        resMap.put("success", "购买成功！");
        return resMap;
    }

    /**
     * 续费
     * @param paramMap
     */
    @Transactional
    @Override
    public Map<String, String> rsqRenewal(Map<String, Object> paramMap) {
        //定义返回值
        Map<String, String> resMap = new HashMap<>();

        logger.debug("====续费start");
        Integer teamId = (Integer) paramMap.get("teamId");
        String productName = (String) paramMap.get("productName");

        // 获取公司版本信息
        RsqTeamVersion rsqTeamVersion = rsqCommonService.getTeamVersionByType(productName);
        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamIdAndTeamVersionId(
                Long.parseLong(String.valueOf(teamId)),
                Long.parseLong(String.valueOf(rsqTeamVersion.getId()))
        );


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
        //添加充值记录
        RsqPayOrder rsqPayOrder = addPayOrder(rsqTeamStatus, paramMap);
        // 添加充值人员操作记录
        RsqPayOperator rsqPayOperator = addPayOperator(rsqPayOrder, paramMap);
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
    public Map<String, String> rsqAdd(Map<String, Object> paramMap) {
        Map<String, String> resMap = new HashMap<>();
        logger.debug("====增加人数start");
        Integer teamId = (Integer) paramMap.get("teamId");
        String productName = (String) paramMap.get("productName");

        // 获取公司版本信息
        RsqTeamVersion rsqTeamVersion = rsqCommonService.getTeamVersionByType(productName);
        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamIdAndTeamVersionId(
                Long.parseLong(String.valueOf(teamId)),
                Long.parseLong(String.valueOf(rsqTeamVersion.getId()))
        );


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
        //添加充值记录
        RsqPayOrder rsqPayOrder = addPayOrder(rsqTeamStatus, paramMap);
        // 添加充值人员操作记录
        RsqPayOperator rsqPayOperator = addPayOperator(rsqPayOrder, paramMap);
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
    public Map<String, String> rsqUpdate(Map<String, Object> paramMap) {
        Map<String, String> resMap = new HashMap<>();
        logger.debug("====版本更新start");
        Integer teamId = (Integer) paramMap.get("teamId");
        String productName = (String) paramMap.get("productName");

        // 获取公司版本信息
        RsqTeamVersion rsqTeamVersion = rsqCommonService.getTeamVersionByType(productName);
        //获取teamStatus
        RsqTeamStatus rsqTeamStatus = this.baseMapper.getRsqTeamStatusByTeamIdAndTeamVersionId(
                Long.parseLong(String.valueOf(teamId)),
                Long.parseLong(String.valueOf(rsqTeamVersion.getId()))
        );


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
        //添加充值记录
        RsqPayOrder rsqPayOrder = addPayOrder(rsqTeamStatus, paramMap);
        // 添加充值人员操作记录
        RsqPayOperator rsqPayOperator = addPayOperator(rsqPayOrder, paramMap);
        logger.debug("====版本更新end");

        resMap.put("success", "版本升级成功！");
        return resMap;
    }

    /**
     * 初始化充值记录信息
     * @param rsqTeamStatus
     * @return
     */
    private void resetTeamStatus(RsqTeamStatus rsqTeamStatus){
        Date today = new Date();
        //会员到期时间修改为今天
        today = CommonUtil.delHHMMSS(today);
        rsqTeamStatus.setDeadLine(today);
        //人数修改为0
        rsqTeamStatus.setUserLimit(0);
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
    private RsqPayOrder addPayOrder(RsqTeamStatus rsqTeamStatus, Map<String, Object> paramMap){
        if (rsqTeamStatus == null || paramMap == null) {
            return null;
        }
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
        rsqPayOrder.setBody(CommonUtil.getBodyForTrial(rsqTeamStatus.getUserLimit(), rsqTeamStatus.getDeadLine()));

        /** plateform设置充值平台 */
        rsqPayOrder.setPlatform("系统");

        /** days 购买天数*/
        rsqPayOrder.setDays((Integer) paramMap.get("days"));

        /** teamid */
        rsqPayOrder.setTeamId((Integer) paramMap.get("teamId"));

        /** payProductId 购买的产品，续费，加人，不填此字段*/
        RsqPayProduct rsqPayProduct = rsqCommonService.getRsqPayProductByTeamVersionId(rsqTeamStatus.getTeamVersionId());
        rsqPayOrder.setPayProductId(rsqPayProduct.getId());

        /** totalFee 订单总价*/
        if(paramMap.get("totalFee") != null){
            rsqPayOrder.setTotalFee(Double.parseDouble((String) paramMap.get("totalFee")));
        }else{
            rsqPayOrder.setTotalFee(0.00);
        }

        /** userLimit 购买人数：升级，续费，不填写此字段*/
        if(paramMap.get("userLimit") != null){
            rsqPayOrder.setUserLimit((Integer) paramMap.get("userLimit"));
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
        rsqPayOrder.setPayType((String) paramMap.get("payType"));

        /** 获取订单号 */
        String outTradeNo = CommonUtil.getOutTradeNo();

        /** transactNo内部交易号 */
        rsqPayOrder.setTransactNo(outTradeNo);

        /** outTradeNo订单号*/
        rsqPayOrder.setOutTradeNo(outTradeNo);
        paramMap.put("outTradeNo",outTradeNo);

        //插入数据库
        this.baseMapper.addPayOrder(rsqPayOrder);

        return rsqPayOrder;
    }

    /**
     * 添加充值操作人员记录
     * @param paramMap 参数列
     * @return void
     * @author codingR
     * @date 2019/4/17 15:02
     */
    private RsqPayOperator addPayOperator(RsqPayOrder rsqPayOrder, Map<String, Object> paramMap){
        if (rsqPayOrder == null || paramMap == null) {
            return null;
        }

        logger.debug("==== 添加操作人员记录 ===");
        // 创建操作记录对象
        RsqPayOperator operator = new RsqPayOperator();

        // 设置名字
        operator.setName((String) paramMap.get("operator"));

        // 设置订单号
        operator.setOutTradeNo((String) paramMap.get("outTradeNo"));

        // 设置用户 id
        operator.setManageId((String) paramMap.get("manageId"));

        addPayOperator(operator);

        return operator;

    }
    /**
     * 添加充值操作人员记录
     * @param rsqPayOperator 操作人员记录
     * @return void
     * @author codingR
     * @date 2019/4/17 15:02
     */
    private void addPayOperator(RsqPayOperator rsqPayOperator) {
        this.baseMapper.addPayOperator(rsqPayOperator);
    }

    /**
     * 添加teamStatus
     * @param paramMap
     */
    private RsqTeamStatus addTeamStatus(Map<String, Object> paramMap){
        try {
            Date gameOver = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2100-01-01 00:00:00");
            Date date = new Date();
            date = CommonUtil.delHHMMSS(date);

            RsqTeamStatus rsqTeamStatus = new RsqTeamStatus();
            //1、固定值0
            rsqTeamStatus.setVersion(0);

            //2、会员版本
            // 通过 type 获取会员版本信息
            RsqTeamVersion rsqTeamVersion = rsqCommonService.getTeamVersionByType((String) paramMap.get("productName"));
            rsqTeamStatus.setTeamVersionId(rsqTeamVersion.getId());

            //3、是否过期,新创建的默认都是未过期的
            rsqTeamStatus.setExpired(false);

            //4、创建时间
            rsqTeamStatus.setDateCreated(date);

            //5、更新时间
            rsqTeamStatus.setLastUpdated(date);

            //6、人数
            Integer addMember = (Integer) paramMap.get("userLimit");
            if(addMember != null && !"".equals(addMember)){
                rsqTeamStatus.setUserLimit(addMember);
            }else{
                rsqTeamStatus.setUserLimit(0);
            }

            //7、公司
            rsqTeamStatus.setTeamId((Integer) paramMap.get("teamId"));

            //8、到期时间
            Integer addDay = (Integer) paramMap.get("days");
            if(addDay != null && !"".equals(addDay)){
                Date res = CommonUtil.addDays(date, addDay);
                if(res.getTime() > gameOver.getTime()) {
                    res = gameOver;
                }
                rsqTeamStatus.setDeadLine(res);
            }else{
                Date res = CommonUtil.addDays(date, 1);
                if(res.getTime() > gameOver.getTime()) {
                    res = gameOver;
                }
                rsqTeamStatus.setDeadLine(res);
            }
            //插入数据库
            this.baseMapper.addTeamStatus(rsqTeamStatus);
            return rsqTeamStatus;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新teamStatus
     */
    private void updateTeamStatus(RsqTeamStatus rsqTeamStatus,Map<String, Object> paramMap){
        //1、人数更新
        Integer addMember = (Integer) paramMap.get("userLimit");
        if(addMember != null && !"".equals(addMember)){
            rsqTeamStatus.setUserLimit(rsqTeamStatus.getUserLimit() + addMember);
        }
        //2、天数更新
        Integer addDay = (Integer) paramMap.get("days");
        if(addDay != null && !"".equals(addDay)){
            //当前到期日期
            Date deadLine = rsqTeamStatus.getDeadLine();
            rsqTeamStatus.setDeadLine(CommonUtil.addDays(deadLine, addDay));
        }
        //3、状态更新
        rsqTeamStatus.setExpired(false);
        //4、更新日期
        rsqTeamStatus.setLastUpdated(new Date());
        //更新teamStatus
        this.baseMapper.updateTeamStatus(rsqTeamStatus);
    }
}
