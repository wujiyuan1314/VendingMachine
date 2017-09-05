package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendRole;

public interface VendRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendRole record);

    int insertSelective(VendRole record);

    VendRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendRole record);

    int updateByPrimaryKey(VendRole record);
    /**下面是自定义方法*/
    List<VendRole> listVendRole(@Param("vendRole") VendRole vendRole, @Param("page") Page page);
    
    int countVendRole(VendRole vendRole);
    
    void insertBatch(List<VendRole> list);
    
    int deleteBatch(int ids[]);
    
    List<VendRole> findAll();
    
    List<VendRole> findNext(Integer roleId);
    
    VendRole selectByRoleName(String roleName);
}