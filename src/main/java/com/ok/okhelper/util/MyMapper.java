package com.ok.okhelper.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by zc on 2018/4/10.
 * 通用Mapper自动生成  api请见：https://blog.csdn.net/fangwenzheng88/article/details/78713091
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
