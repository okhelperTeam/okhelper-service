package com.ok.okhelper.common;

import com.github.pagehelper.PageInfo;
import com.ok.okhelper.exception.IllegalException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/10.
 */

@Data
@SuppressWarnings("unchecked")
public class PageModel<T> {
    /*
    分页模型
    * */
    @NotNull
    @AssertTrue(message = "分页参数错误(paging不能为空)")
    @ApiModelProperty(value = "开启分页", required = true, notes = "必须传true分页才能开启")
    private boolean paging = true;

    //请求页码
    @NotNull(message = "分页参数错误(pageNum不能为空)")
    @ApiModelProperty(value = "请求页码", required = true)
    private Integer pageNum;

    //每页多少条
    @NotNull(message = "分页参数错误(limit不能为空)")
    @ApiModelProperty(value = "每页多少条", required = true)
    private Integer limit;

    //分页总数
    @ApiModelProperty(value = "总页数(返回数据不用传)")
    private int pageCount;


    //总记录数
    @ApiModelProperty(value = "总记录数(返回数据不用传)")
    private long total;


    //数据
    @ApiModelProperty(value = "数据(返回数据不用传)")
    private List<T> results = new ArrayList<>();


    public static PageModel convertToPageModel(PageInfo pageResult) {
        PageModel pageModel = new PageModel();
        if (pageResult.getSize() == 0 && pageResult.getPageNum() > 1) {
            throw new IllegalException("没有更多了");
        }
        pageModel.setPageNum(pageResult.getPageNum());
        pageModel.setLimit(pageResult.getPageSize());
        pageModel.setPageCount(pageResult.getPages());
        pageModel.setTotal(pageResult.getTotal());
        pageModel.setResults(pageResult.getList());
        return pageModel;
    }

}
