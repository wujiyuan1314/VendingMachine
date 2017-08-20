package vend.dao;

import vend.entity.VendPermission;

public interface VendPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendPermission record);

    int insertSelective(VendPermission record);

    VendPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendPermission record);

    int updateByPrimaryKey(VendPermission record);
}