package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendPermission;

public interface VendPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendPermission record);

    int insertSelective(VendPermission record);

    VendPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendPermission record);

    int updateByPrimaryKey(VendPermission record);
    /**下为自定义方法 */
    List<VendPermission> listVendPermission(@Param("vendPermission") VendPermission vendPermission, @Param("page") Page page);
    
    int countVendPermission(VendPermission vendPermission);
    
    void insertBatch(List<VendPermission> list);
    
    int deleteBatch(int ids[]);
    
    List<VendPermission> findAll();
    
    VendPermission selectByPermissionName(String permissionName);
}