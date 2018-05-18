package com.ok.okhelper.dao;

import com.ok.okhelper.pojo.bo.IdAndNameBo;
import com.ok.okhelper.pojo.po.Product;
import com.ok.okhelper.pojo.vo.CategoryVo;
import com.ok.okhelper.pojo.vo.ProductStockVo;
import com.ok.okhelper.pojo.vo.ProductsVo;
import com.ok.okhelper.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ProductMapper extends MyMapper<Product> {
    int cutSalesStock(@Param("salesCount") Integer salesCount, @Param("productId") Long productId);

    int addSalesStock(@Param("salesCount") Integer salesCount, @Param("productId") Long productId);

    List<ProductsVo> getProductsList(@Param("condition") String condition,@Param("storeId")Long storeId);

    /*
    * @Author zhangxin_an 
    * @Date 2018/4/30 18:09  
    * @Params [cId]  
    * @Return java.util.List<com.ok.okhelper.pojo.vo.ProductsVo>  
    * @Description:ͨ�������ѯ��Ʒ
    */  
    List<ProductsVo> getProductsListByCategoryId(@Param("storeId") Long storeId,@Param("categoryListTotal") List<CategoryVo> categoryListTotal);

    List<ProductsVo> getProductsListBybarCode(@Param("barCode") String barCode,@Param("storeId")Long storeId);
    
    IdAndNameBo getIdAndName(Long id);
    
    void updateStatus(@Param("pId") Long pId, @Param("storeId") Long storeId);

    Integer getSaleStockLock(Long productId);
    
    ProductStockVo getProductStockVoByPK(Long productId);
}