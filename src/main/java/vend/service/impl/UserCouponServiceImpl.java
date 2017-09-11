package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Page;
import vend.dao.UserCouponMapper;
import vend.entity.UserCoupon;
import vend.service.UserCouponService;

@Service
public class UserCouponServiceImpl implements UserCouponService {
	@Autowired
	UserCouponMapper userCouponMapper;
	/**
	 * 根据输入信息条件查询消费用户优惠券列表，并分页显示
	 * @param UserCoupon
	 * @param page
	 * @return
	 */
	public List<UserCoupon> listUserCoupon(UserCoupon userCoupon,Page page){
		int totalNumber = userCouponMapper.countUserCoupon(userCoupon);
		page.setTotalNumber(totalNumber);
		return userCouponMapper.listUserCoupon(userCoupon, page);
	}
	/**
	 * 添加消费用户优惠券
	 * @param UserCoupon
	 * @return
	 */
	public int insertUserCoupon(UserCoupon userCoupon){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		userCoupon.setCreateTime(createTime);
		return userCouponMapper.insertSelective(userCoupon);
	}
	/**
	 * 修改消费用户优惠券
	 * @param UserCoupon
	 * @return
	 */
	public int editUserCoupon(UserCoupon userCoupon){
		return userCouponMapper.updateByPrimaryKeySelective(userCoupon);
	}
	/**
	 * 删除�?个商�?
	 * @param id
	 */
	public void delUserCoupon(int id){
		userCouponMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除消费用户优惠券
	 * @param id
	 */
	public int delUserCoupons(int ids[]){
		return userCouponMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	public UserCoupon getOne(int id){
		return userCouponMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查找全部
	 * @return
	 */
	public List<UserCoupon> findAll() {
		// TODO Auto-generated method stub
		return userCouponMapper.findAll();
	}
	/**
	 * 按照usercode查找
	 * @return
	 */
	public List<UserCoupon> findByUsercode(String usercode){
		return userCouponMapper.findByUsercode(usercode);
	}
}
