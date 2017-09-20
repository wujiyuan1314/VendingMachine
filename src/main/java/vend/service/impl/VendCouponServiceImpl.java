package vend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.util.CacheUtils;
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
	public List<VendCoupon> listVendCoupon(VendCoupon vendCoupon,Page page){
		String title=vendCoupon.getCouponName();
		String currentPage=Integer.toString(page.getCurrentPage());
		if(title==null){
			title="";
		}
		String key="key_listVendCoupon"+title+currentPage;
		List<VendCoupon> vendCoupons=(List<VendCoupon>)CacheUtils.get("couponCache", key);
		if(vendCoupons==null){
			int totalNumber = vendCouponMapper.countVendCoupon(vendCoupon);
			page.setTotalNumber(totalNumber);
			vendCoupons=vendCouponMapper.listVendCoupon(vendCoupon, page);
			CacheUtils.put("couponCache",key, vendCoupons);
		}
		return vendCoupons;
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
		int isOk=vendCouponMapper.insertSelective(vendCoupon);
		if(isOk==1){
			CacheUtils.remove("adCache", "key_VendCoupon_findAll");
		}
		return isOk;
	}
	/**
	 * 修改优惠券
	 * @param VendCoupon
	 * @return
	 */
	public int editVendCoupon(VendCoupon vendCoupon){
		int isOk=vendCouponMapper.updateByPrimaryKeySelective(vendCoupon);
		if(isOk==1){
			CacheUtils.remove("adCache", "key_VendCoupon_findAll");
		}
		return isOk;
	}
	/**
	 * 删除�?个商�?
	 * @param id
	 */
	public void delVendCoupon(int id){
		int isOk=vendCouponMapper.deleteByPrimaryKey(id);
		if(isOk==1){
			CacheUtils.remove("adCache", "key_VendCoupon_findAll");
		}
	}
	/**
	 * 批量删除优惠券
	 * @param id
	 */
	public int delVendCoupons(int ids[]){
		int isOk=vendCouponMapper.deleteBatch(ids);
		if(isOk==1){
			CacheUtils.remove("adCache", "key_VendCoupon_findAll");
		}
		return isOk;
	}
	/**
	 * 根据ID查找�?个商�?
	 * @param id
	 * @return
	 */
	public VendCoupon getOne(int id){
		String key="VendAd_getOne"+id;
		VendCoupon vendCoupon=(VendCoupon)CacheUtils.get("couponCache", key);
		if(vendCoupon==null){
			vendCoupon=vendCouponMapper.selectByPrimaryKey(id);
			CacheUtils.put("couponCache", key, vendCoupon);
		}
		return vendCoupon;
	}

	public List<VendCoupon> findAll() {
		// TODO Auto-generated method stub
		String key="key_VendCoupon_findAll";
		List<VendCoupon> vendCoupons=(List<VendCoupon>)CacheUtils.get("couponCache", key);
		if(vendCoupons==null){
			vendCoupons= vendCouponMapper.findAll();
			CacheUtils.put("couponCache", key, vendCoupons);
		}
		return vendCoupons;
	}
}
