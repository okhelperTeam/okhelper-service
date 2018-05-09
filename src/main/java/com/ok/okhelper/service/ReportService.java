package com.ok.okhelper.service;

import com.ok.okhelper.common.PageModel;
import com.ok.okhelper.pojo.bo.CustomerDebtBo;
import com.ok.okhelper.pojo.vo.SaleTotalVo;
import com.ok.okhelper.pojo.vo.SalesVolumeVo;

import java.util.Date;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/6
 * Description:
 */
public interface ReportService {
    PageModel<CustomerDebtBo> getCustomerDebt(String condition, PageModel pageModel);

    List<SalesVolumeVo> getHotSaleByRedis(String range);

    List<SalesVolumeVo> getUnsalableByRedis(String range);

    SaleTotalVo getSaleTotalVo(Date startDate, Date endDate);
}
