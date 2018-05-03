package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/*
*Author:zhangxin_an
*Description:入库单子项
*Data:Created in 10:14 2018/5/3
*/
@Data
public class StorageDetailDto {
	/**
	 * 仓库
	 */
	@ApiModelProperty(value = "仓库Id",required = true)
	@NotNull
	private Long warehouseId;
	
	/**
	 * 商品
	 */
	@ApiModelProperty(value = "商品Id",required = true)
	@NotNull
	private Long productId;
	
	/**
	 * 商品数量(最小单位)
	 */
	@ApiModelProperty(value = "商品数量（最小单位）",required = true)
	@NotNull
	private Integer storageCount;
	
	/**
	 * 进价 (最小单位)
	 */
	@ApiModelProperty(value = "单价(最小单位)",required = true)
	@NotNull
	private BigDecimal storagePrice;
	
	/**
	 * 生产日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "生产日期",required = true)
	@NotNull
	private Date productDate;
	
	/**
	 * 保质期
	 */
	@ApiModelProperty(value = "保质期",required = true)
	@NotNull
	private Integer shelfLife;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remarks;
	
	public StorageDetailDto(@NotNull Long warehouseId, @NotNull Long productId, @NotNull Integer storageCount, @NotNull BigDecimal storagePrice, @NotNull  Date productDate, @NotNull Integer shelfLife, String remarks) {
		this.warehouseId = warehouseId;
		this.productId = productId;
		this.storageCount = storageCount;
		this.storagePrice = storagePrice;
		this.productDate = productDate;
		this.shelfLife = shelfLife;
		this.remarks = remarks;
	}
	
	public StorageDetailDto() {
	}
}
