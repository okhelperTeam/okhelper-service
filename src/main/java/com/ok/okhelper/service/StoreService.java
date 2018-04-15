package com.ok.okhelper.service;

import com.ok.okhelper.pojo.dto.StoreDto;
import com.ok.okhelper.pojo.po.Store;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/15
 */
public interface StoreService {
    Store postStore(StoreDto storeDto);
}
