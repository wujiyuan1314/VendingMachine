package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
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
	@Cacheable(value="couponCache")
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
		int isOk=userCouponMapper.insertSelective(userCoupon);
		if(isOk==1){
			CacheUtils.remove("couponCache","key_UserCoupon_findAll");
		}
		return isOk;
	}
	/**
	 * 修改消费用户优惠券
	 * @param UserCoupon
	 * @return
	 */
	public int editUserCoupon(UserCoupon userCoupon){
		int isOk= userCouponMapper.updateByPrimaryKeySelective(userCoupon);
		if(isOk==1){
			CacheUtils.remove("couponCache","key_UserCoupon_findAll");
		}
		return isOk;
	}
	/**
	 * 删除�?个商�?
	 * @param id
	 */
	public void delUserCoupon(int id){
		int isOk= userCouponMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.remove("couponCache","key_UserCoupon_findAll");
		}
	}
	/**
	 * 批量删除消费用户优惠券
	 * @param id
	 */
	public int delUserCoupons(int ids[]){
		int isOk= userCouponMapper.deleteBatch(ids);
		if(isOk==1){
			CacheUtils.remove("couponCache","key_UserCoupon_findAll");
		}
		return isOk;
	}
	/**
	 * 批量添加
	 * @param list
	 */
	public void insertBatch(List<UserCoupon> list){
		userCouponMapper.insertBatch(list);
	}
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	@Cacheable(value="couponCache")
	public UserCoupon getOne(int id){
		String key="key_UserCoupon_getOne";
		UserCoupon userCoupon=(UserCoupon)CacheUtils.get("couponCache", key);
		if(userCoupon==null){
			userCoupon=userCouponMapper.selectByPrimaryKey(id);
			CacheUtils.put("couponCache", key, userCoupon);
		}
		return userCoupon;
	}
	/**
	 * 查找全部
	 * @return
	 */
	public List<UserCoupon> findAll() {
		// TODO Auto-generated method stub
		String key="key_UserCoupon_findAll";
		List<UserCoupon> userCoupons=(List<UserCoupon>)CacheUtils.get("couponCache", key);
		if(userCoupons==null){
			userCoupons=userCouponMapper.findAll();
			CacheUtils.put("couponCache", key, userCoupons);
		}
		return userCoupons;
	}
	/**
	 * 按照usercode查找
	 * @return
	 */
	public List<UserCoupon> findByUsercode(String usercode){
		String key="key_UserCoupon_findByUsercode"+usercode;
		List<UserCoupon> userCoupons=(List<UserCoupon>)CacheUtils.get("couponCache", key);
		if(userCoupons==null){
			userCoupons=userCouponMapper.findByUsercode(usercode);
			CacheUtils.put("couponCache", key, userCoupons);
		}
		return userCoupons;
	}
	/**
	 * 按照usercode和优惠券ID查找
	 * @param usercode
	 * @param couponId
	 */
	public UserCoupon findByUsercodeLimitCouponId(String usercode,Integer couponId){
		String key="key_UserCoupon_findByUsercodeLimitCouponId"+usercode+couponId;
		UserCoupon userCoupon=(UserCoupon)CacheUtils.get("couponCache", key);
		if(userCoupon==null){
			userCoupon=userCouponMapper.findByUsercodeLimitCouponId(usercode, couponId);
			CacheUtils.put("couponCache", key, userCoupon);
		}
		return userCoupon;
	}
	/**
	 * 按照当前时间查找
	 * @return
	 */
	public List<UserCoupon> findByEndtime(String CurrentDate){
		String key="key_UserCoupon_findByEndtime"+CurrentDate;
		List<UserCoupon> userCoupons=(List<UserCoupon>)CacheUtils.get("couponCache", key);
		if(userCoupons==null){
			userCoupons=userCouponMapper.findByEndtime(CurrentDate);
			CacheUtils.put("couponCache", key, userCoupons);
		}
		return userCoupons;
	}
}
