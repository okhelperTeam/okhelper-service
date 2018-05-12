package com.ok.okhelper.common;

import com.github.pagehelper.PageInfo;
import com.ok.okhelper.exception.IllegalException;
import com.ok.okhelper.exception.NotFoundException;
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
//    @AssertTrue(message = "分页参数错误(paging不能为空)")
    @ApiModelProperty(value = "开启分页(必须传true分页才能开启)", required = true)
    private boolean paging = true;

    //请求页码
    @NotNull(message = "分页参数错误(pageNum不能为空)")
    @Min(1)
    @ApiModelProperty(value = "请求页码", required = true)
    private Integer pageNum;

    //每页多少条
    @NotNull(message = "分页参数错误(limit不能为空)")
    @Min(0)
    @ApiModelProperty(value = "每页多少条", required = true)
    private Integer limit;

    //分页总数
    @ApiModelProperty(value = "总页数(返回数据不用传)")
    private int pageCount;

    //总记录数
    @ApiModelProperty(value = "总记录数(返回数据不用传)")
    private long total;

    //是否是最后一页
    @ApiModelProperty(value = "是否是最后一页(返回数据不用传)")
    private boolean lastPage;

    //排序规则
    @ApiModelProperty(value = "排序参数(格式为：'create_time desc' 或者 'create_time asc' 注意前面的是数据库的字段名，不传默认按时间倒序)")
    private String orderBy = "create_time desc";

    //聚合数据
    @ApiModelProperty(value = "聚合数据汇总")
    private Object totalData;

    //数据
    @ApiModelProperty(value = "数据(返回数据不用传)")
    private List<T> results = new ArrayList<>();


    public void setPaging(boolean paging) {
        if(!paging){
            pageNum=1;
            limit=0;
        }
        this.paging = paging;
    }

    public static <T> PageModel<T> convertToPageModel(PageInfo pageResult) {
        if (pageResult.getTotal() <= 0) {
            throw new NotFoundException("没有找到相关数据");
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageNum(pageResult.getPageNum());
        pageModel.setLimit(pageResult.getPageSize());
        pageModel.setPageCount(pageResult.getPages());
        pageModel.setTotal(pageResult.getTotal());
        pageModel.setResults(pageResult.getList());
        pageModel.setLastPage(pageResult.isIsLastPage());
        return pageModel;
    }

}
