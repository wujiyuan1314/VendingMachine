package vend.service;

import java.util.List;

import base.util.Page;
import vend.entity.VendCoupon;

public interface VendCouponService {
	/**
	 * 根据输入信息条件查询优惠券列表，并分页显示
	 * @param VendCoupon
	 * @param page
	 * @return
	 */
	List<VendCoupon> listVendCoupon(VendCoupon vendCoupon,Page page);
	/**
	 * 添加优惠券
	 * @param VendCoupon
	 * @return
	 */
	int insertVendCoupon(VendCoupon vendCoupon);
	/**
	 * 修改优惠券
	 * @param VendCoupon
	 * @return
	 */
	int editVendCoupon(VendCoupon vendCoupon);
	/**
	 * 删除�?个商�?
	 * @param id
	 */
	void delVendCoupon(int id);
	/**
	 * 批量删除优惠券
	 * @param id
	 */
	int delVendCoupons(int id[]);
	/**
	 * 根据ID查找�?个商�?
	 * @param id
	 * @return
	 */
	VendCoupon getOne(int id);
	/**
	 * 查找优惠活动
	 * @param currentDate
	 * @return
	 */
	List<VendCoupon> selectRecharge(String currentDate);
	/**
	 * 查找全部
	 * @return
	 */
	List<VendCoupon> findAll();
	
}
