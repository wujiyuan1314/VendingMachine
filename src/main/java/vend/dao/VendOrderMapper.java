package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendOrder;

public interface VendOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(VendOrder record);

    int insertSelective(VendOrder record);

    VendOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(VendOrder record);

    int updateByPrimaryKey(VendOrder record);
    /**下为自定义方法 */
    List<VendOrder> listVendOrder(@Param("vendOrder") VendOrder vendOrder, @Param("page") Page page);
    
    int countVendOrder(VendOrder vendOrder);
    
    void insertBatch(List<VendOrder> list);
    
    int deleteBatch(String orderId[]);
}