package vend.dao;

import vend.entity.VendAd;

public interface VendAdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendAd record);

    int insertSelective(VendAd record);

    VendAd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendAd record);

    int updateByPrimaryKey(VendAd record);
}