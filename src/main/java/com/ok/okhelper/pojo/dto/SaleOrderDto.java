package com.ok.okhelper.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Author: zc
 * Date: 2018/4/23
 * Description:
 */
@Data
public class SaleOrderDto {

    @NotNull(message = "开始时间不能为空")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间", required = true)
    private Date startDate;

    @NotNull(message = "结束时间不能为空")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间", required = true)
    private Date endDate;

}
