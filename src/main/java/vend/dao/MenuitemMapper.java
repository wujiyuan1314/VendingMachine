package vend.dao;

import vend.entity.Menuitem;

public interface MenuitemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menuitem record);

    int insertSelective(Menuitem record);

    Menuitem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menuitem record);

    int updateByPrimaryKey(Menuitem record);
}