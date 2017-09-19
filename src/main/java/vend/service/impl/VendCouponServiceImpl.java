package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import base.util.DateUtil;
import base.util.Page;
import vend.dao.VendCouponMapper;
import vend.entity.VendCoupon;
import vend.service.VendCouponService;

@Service
public class VendCouponServiceImpl implements VendCouponService {
	@Autowired
	VendCouponMapper vendCouponMapper;
	/**
	 * 根据输入信息条件查询优惠券列表，并分页显示
	 * @param VendCoupon
	 * @param page
	 * @return
	 */
	@Cacheable(value="couponCache")
	public List<VendCoupon> listVendCoupon(VendCoupon vendCoupon,Page page){
		int totalNumber = vendCouponMapper.countVendCoupon(vendCoupon);
		page.setTotalNumber(totalNumber);
		return vendCouponMapper.listVendCoupon(vendCoupon, page);
	}
	/**
	 * 添加优惠券
	 * @param VendCoupon
	 * @return
	 */
	public int insertVendCoupon(VendCoupon vendCoupon){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendCoupon.setCreateTime(createTime);
		vendCoupon.setUpdateTime(createTime);
		return vendCouponMapper.insertSelective(vendCoupon);
	}
	/**
	 * 修改优惠券
	 * @param VendCoupon
	 * @return
	 */
	public int editVendCoupon(VendCoupon vendCoupon){
		return vendCouponMapper.updateByPrimaryKeySelective(vendCoupon);
	}
	/**
	 * 删除�?个商�?
	 * @param id
	 */
	public void delVendCoupon(int id){
		vendCouponMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 批量删除优惠券
	 * @param id
	 */
	public int delVendCoupons(int ids[]){
		return vendCouponMapper.deleteBatch(ids);
	}
	/**
	 * 根据ID查找�?个商�?
	 * @param id
	 * @return
	 */
	@Cacheable(value="couponCache")
	public VendCoupon getOne(int id){
		return vendCouponMapper.selectByPrimaryKey(id);
	}
	@Cacheable(value="couponCache")
	public List<VendCoupon> findAll() {
		// TODO Auto-generated method stub
		return vendCouponMapper.findAll();
	}
}
