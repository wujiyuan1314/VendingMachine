package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendAccount;

public interface VendAccountMapper {
    int deleteByPrimaryKey(String usercode);

    int insert(VendAccount record);

    int insertSelective(VendAccount record);

    VendAccount selectByPrimaryKey(String usercode);

    int updateByPrimaryKeySelective(VendAccount record);

    int updateByPrimaryKey(VendAccount record);
    /**下为自定义方法 */
    List<VendAccount> listVendAccount(@Param("vendAccount") VendAccount vendAccount, @Param("page") Page page);
    
    int countVendAccount(VendAccount vendAccount);
    
    void insertBatch(List<VendAccount> list);
    
    int deleteBatch(String usercodes[]);
    
    List<VendAccount> findAll();
}