package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.VendCoupon;

public interface VendCouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VendCoupon record);

    int insertSelective(VendCoupon record);

    VendCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VendCoupon record);

    int updateByPrimaryKey(VendCoupon record);
    /**自定义方法*/
    List<VendCoupon> listVendCoupon(@Param("vendCoupon") VendCoupon vendCoupon, @Param("page") Page page);
    
    int countVendCoupon(VendCoupon vendCoupon);
    
    List<VendCoupon> selectRecharge(String currentDate);
    
    int deleteBatch(int ids[]);
    
    List<VendCoupon> findAll();
}