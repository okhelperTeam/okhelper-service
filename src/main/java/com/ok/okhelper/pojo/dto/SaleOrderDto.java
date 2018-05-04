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

//    @ApiModelProperty(value = "排序参数(格式为：'create_time desc' 或者 'create_time asc' 注意前面的是数据库的字段名，不传默认按时间正序)")
//    private String orderBy = "create_time asc";
}
