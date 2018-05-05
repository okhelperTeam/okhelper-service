package com.ok.okhelper.pojo.dto;

import com.ok.okhelper.pojo.po.DeliveryOrderDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:
 */
@Data
public class DeliveryDto {

    @ApiModelProperty(value = "销售单Id", hidden = true)
    private Long saleOrderId;

    private List<DeliverItemDto> deliverItemDtos;
}
