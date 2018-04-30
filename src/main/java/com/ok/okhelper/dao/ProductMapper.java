package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.until.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends MyMapper<Product> {
    int cutSalesStock(@Param("salesCount") Integer salesCount,@Param("productId") Long productId);

    int addSalesStock(@Param("salesCount") Integer salesCount, @Param("productId") Long productId);
}