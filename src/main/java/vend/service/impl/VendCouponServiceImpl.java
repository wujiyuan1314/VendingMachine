package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<VendCoupon> listVendCoupon(VendCoupon VendCoupon,Page page){
		int totalNumber = vendCouponMapper.countVendCoupon(VendCoupon);
		page.setTotalNumber(totalNumber);
		return vendCouponMapper.listVendCoupon(VendCoupon, page);
	}
	/**
	 * 添加优惠券
	 * @param VendCoupon
	 * @return
	 */
	public int insertVendCoupon(VendCoupon VendCoupon){
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		VendCoupon.setCreateTime(createTime);
		VendCoupon.setUpdateTime(createTime);
		return vendCouponMapper.insertSelective(VendCoupon);
	}
	/**
	 * 修改优惠券
	 * @param VendCoupon
	 * @return
	 */
	public int editVendCoupon(VendCoupon VendCoupon){
		return vendCouponMapper.updateByPrimaryKeySelective(VendCoupon);
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
	public VendCoupon getOne(int id){
		return vendCouponMapper.selectByPrimaryKey(id);
	}
	public List<VendCoupon> findAll() {
		// TODO Auto-generated method stub
		return vendCouponMapper.findAll();
	}
}
