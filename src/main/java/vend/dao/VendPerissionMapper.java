package vend.dao;

import vend.entity.VendPerission;

public interface VendPerissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendPerission record);

    int insertSelective(VendPerission record);

    VendPerission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendPerission record);

    int updateByPrimaryKey(VendPerission record);
}