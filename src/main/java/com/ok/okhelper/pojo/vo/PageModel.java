package com.ok.okhelper.pojo.vo;

import com.github.pagehelper.PageInfo;
import com.ok.okhelper.exception.IllegalException;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/10.
 */

@Data
public class PageModel<T> {
/*
分页模型
* */
    @NotNull
    @AssertTrue(message = "分页参数错误(paging)")
    private boolean paging=true;

    //请求页码
    @NotNull(message = "分页参数错误(pageNum)")
    private Integer pageNum;

    //每页多少条
    @NotNull(message = "分页参数错误(limit)")
    private Integer limit;

    //分页总数
    private int pageCount;


    //总记录数
    private long total;


    //数据
    private List<T> results=new ArrayList<>();


    public static PageModel convertToPageModel(PageInfo pageResult){
        PageModel pageModel=new PageModel();
        if(pageResult.getSize()==0&&pageResult.getPageNum()>1){throw new IllegalException("没有更多了"); }
        pageModel.setPageNum(pageResult.getPageNum());
        pageModel.setLimit(pageResult.getPageSize());
        pageModel.setPageCount(pageResult.getPages());
        pageModel.setTotal(pageResult.getTotal());
        pageModel.setResults(pageResult.getList());
        return pageModel;
    }

}
