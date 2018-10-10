package com.rishiqing.modules.payrecord.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Queryable;
import com.rishiqing.modules.payrecord.entity.RsqPayRecord;

import javax.servlet.http.HttpServletRequest;

/**   
 * @Title: 充值消费记录
 * @Description: 充值消费记录
 * @author rishiqing
 * @date 2018-09-20 10:29:50
 * @version V1.0   
 *
 */
public interface IRsqPayRecordService extends ICommonService<RsqPayRecord> {

    Page<RsqPayRecord> ajaxList(Queryable queryable, HttpServletRequest request);
}

