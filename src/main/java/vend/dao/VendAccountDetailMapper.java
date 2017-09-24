package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendAccountDetail;

public interface VendAccountDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendAccountDetail record);

    int insertSelective(VendAccountDetail record);

    VendAccountDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendAccountDetail record);

    int updateByPrimaryKey(VendAccountDetail record);
    /**下为自定义方法 */
    List<VendAccountDetail> listVendAccountDetail(@Param("vendAccountDetail") VendAccountDetail vendAccountDetail, @Param("page") Page page);
    
    int countVendAccountDetail(VendAccountDetail vendAccountDetail);
    
    List<VendAccountDetail> listVendAccountDetailTx(@Param("vendAccountDetail") VendAccountDetail vendAccountDetail, @Param("page") Page page);
    
    int countVendAccountDetailTx(VendAccountDetail vendAccountDetail);
    
    int deleteBatch(int ids[]);
    
    List<VendAccountDetail> findAll();
}