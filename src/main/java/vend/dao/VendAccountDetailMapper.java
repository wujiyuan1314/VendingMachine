package vend.dao;

import vend.entity.VendAccountDetail;

public interface VendAccountDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendAccountDetail record);

    int insertSelective(VendAccountDetail record);

    VendAccountDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendAccountDetail record);

    int updateByPrimaryKey(VendAccountDetail record);
}