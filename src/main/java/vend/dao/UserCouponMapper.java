package vend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import base.util.Page;
import vend.entity.UserCoupon;

public interface UserCouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCoupon record);

    int insertSelective(UserCoupon record);

    UserCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCoupon record);

    int updateByPrimaryKey(UserCoupon record);
    /**自定义方法*/
    List<UserCoupon> listUserCoupon(@Param("userCoupon") UserCoupon userCoupon, @Param("page") Page page);
    
    int countUserCoupon(UserCoupon userCoupon);
    
    void insertBatch(List<UserCoupon> list);
    
    int deleteBatch(int ids[]);
    
    List<UserCoupon> findAll();
    
    List<UserCoupon> findByUsercode(String usercode);
    
    UserCoupon findByUsercodeLimitCouponId(@Param("usercode") String usercode,@Param("couponId") Integer couponId);
    
    List<UserCoupon> findByEndtime(String CurrentDate);
}