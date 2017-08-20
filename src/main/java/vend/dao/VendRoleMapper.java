package vend.dao;

import vend.entity.VendRole;

public interface VendRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VendRole record);

    int insertSelective(VendRole record);

    VendRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VendRole record);

    int updateByPrimaryKey(VendRole record);
}