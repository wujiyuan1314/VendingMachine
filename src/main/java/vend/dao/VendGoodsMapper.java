package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendGoods;

public interface VendGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendGoods record);

    int insertSelective(VendGoods record);

    VendGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendGoods record);

    int updateByPrimaryKey(VendGoods record);
    /**下为自定义方法 */
    List<VendGoods> listVendGoods(@Param("vendGoods") VendGoods vendGoods, @Param("page") Page page);
    
    int countVendGoods(VendGoods vendGoods);
    
    void insertBatch(List<VendGoods> list);
    
    int deleteBatch(int ids[]);
    
    List<VendGoods> findAll();
}