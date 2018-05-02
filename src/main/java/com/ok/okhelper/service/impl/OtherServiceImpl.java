package com.ok.okhelper.service.impl;

import com.ok.okhelper.dao.ProductMapper;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.pojo.dto.PlaceOrderItemDto;
import com.ok.okhelper.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:
 */
@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * @Author zc
     * @Date 2018/4/28 下午11:57
     * @Param [salesOrderDetails]
     * @Return void
     * @Description:检测并减库存
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean checkAndCutStock(List<PlaceOrderItemDto> placeOrderItemDtos) {
        placeOrderItemDtos.forEach(placeOrderItemDto -> {
            int i = productMapper.cutSalesStock(placeOrderItemDto.getSalesCount(), placeOrderItemDto.getProductId());
            if (i <= 0) {
                throw new IllegalException("商品id：" + placeOrderItemDto.getProductId() + "库存不足下单失败");
            }
        });
        return true;
    }


}
