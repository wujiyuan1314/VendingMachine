package vend.dao;

import vend.entity.VendAccount;

public interface VendAccountMapper {
    int deleteByPrimaryKey(String usercode);

    int insert(VendAccount record);

    int insertSelective(VendAccount record);

    VendAccount selectByPrimaryKey(String usercode);

    int updateByPrimaryKeySelective(VendAccount record);

    int updateByPrimaryKey(VendAccount record);
}