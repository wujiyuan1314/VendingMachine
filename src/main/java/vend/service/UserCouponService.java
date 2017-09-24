package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.UserCoupon;

public interface UserCouponService {
	/**
	 * 根据输入信息条件查询消费用户优惠券列表，并分页显示
	 * @param UserCoupon
	 * @param page
	 * @return
	 */
	List<UserCoupon> listUserCoupon(UserCoupon userCoupon,Page page);
	/**
	 * 添加消费用户优惠券
	 * @param UserCoupon
	 * @return
	 */
	int insertUserCoupon(UserCoupon userCoupon);
	/**
	 * 修改消费用户优惠券
	 * @param UserCoupon
	 * @return
	 */
	int editUserCoupon(UserCoupon userCoupon);
	/**
	 * 删除
	 * @param id
	 */
	void delUserCoupon(int id);
	/**
	 * 批量删除消费用户优惠券
	 * @param id
	 */
	int delUserCoupons(int id[]);
	/**
	 * 批量添加
	 * @param list
	 */
	void insertBatch(List<UserCoupon> list);
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	UserCoupon getOne(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<UserCoupon> findAll();
	/**
	 * 按照usercode查找
	 * @return
	 */
	List<UserCoupon> findByUsercode(String usercode);
	/**
	 * 按照usercode和优惠券ID查找
	 * @param usercode
	 * @param couponId
	 */
	UserCoupon findByUsercodeLimitCouponId(String usercode,Integer couponId);
	/**
	 * 按照当前时间查找
	 * @return
	 */
	List<UserCoupon> findByEndtime(String CurrentDate);
	
}
