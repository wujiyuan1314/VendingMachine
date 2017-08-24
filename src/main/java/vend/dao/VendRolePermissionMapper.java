package vend.dao;

import java.util.List;

import vend.entity.VendRolePermission;

public interface VendRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendRolePermission record);

    int insertSelective(VendRolePermission record);

    VendRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendRolePermission record);

    int updateByPrimaryKey(VendRolePermission record);
    
    List<VendRolePermission> selectByRoleId(Integer roleId);
}