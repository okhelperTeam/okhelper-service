package com.ok.okhelper.service;

import com.ok.okhelper.pojo.dto.UpdateStoreDto;
import com.ok.okhelper.pojo.po.Store;

/**
 * @author: zc
 * @description:
 * @date: 2018/4/15
 */
public interface StoreService {
    Store getStoreInfoById(Long storeId);

    Store updateStore(Long store_id, UpdateStoreDto updateStoreDto);
    
}
