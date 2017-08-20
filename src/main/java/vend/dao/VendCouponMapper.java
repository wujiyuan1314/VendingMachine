package vend.dao;

import vend.entity.VendCoupon;

public interface VendCouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendCoupon record);

    int insertSelective(VendCoupon record);

    VendCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendCoupon record);

    int updateByPrimaryKey(VendCoupon record);
}