package vend.dao;

import vend.entity.VendQrcodeAttend;

public interface VendQrcodeAttendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendQrcodeAttend record);

    int insertSelective(VendQrcodeAttend record);

    VendQrcodeAttend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendQrcodeAttend record);

    int updateByPrimaryKey(VendQrcodeAttend record);
}