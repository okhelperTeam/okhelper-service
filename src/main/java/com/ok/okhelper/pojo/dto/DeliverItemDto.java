package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/5/2
 * Description:
 */
@Data
public class DeliverItemDto {
    /**
     * 商品ID
     */
    @NotNull
    @ApiModelProperty(value = "商品ID", required = true)
    @Column(name = "product_id")
    private Long productId;

    /**
     * 仓库ID
     */
    @NotNull
    @ApiModelProperty(value = "仓库ID", required = true)
    @Column(name = "warehouse_id")
    private Long warehouseId;

    /**
     * 出库数量
     */
    @NotNull
    @ApiModelProperty(value = "出库数量", required = true)
    @Column(name = "delivery_count")
    private Integer deliveryCount;

    /**
     * 生产日期
     */
    @NotNull
    @ApiModelProperty(value = "生产日期", required = true)
    @Column(name = "product_date")
    private Date productDate;
}
