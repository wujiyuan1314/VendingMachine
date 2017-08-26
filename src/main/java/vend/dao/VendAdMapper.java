package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendAd;

public interface VendAdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendAd record);

    int insertSelective(VendAd record);

    VendAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendAd record);

    int updateByPrimaryKey(VendAd record);
    /**下为自定义方法 */
    List<VendAd> listVendAd(@Param("vendAd") VendAd vendAd, @Param("page") Page page);
    
    int countVendAd(VendAd vendAd);
    
    void insertBatch(List<VendAd> list);
    
    int deleteBatch(int ids[]);
    
    List<VendAd> findAll();
}