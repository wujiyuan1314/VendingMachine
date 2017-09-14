package base.task;

import java.util.List;

import org.apache.log4j.Logger;

import base.util.DateUtil;
import vend.dao.UserCouponMapper;
import vend.entity.UserCoupon;
/**
 * 用户优惠券定时任务
 * @author ylsoft
 *
 */

public class UserCouponTask {
	private static Logger log = Logger.getLogger(UserCouponTask.class.getName());
	UserCouponMapper userCouponMapper;
	/**
	* 定时任务入口
	*/
	public void doTask(){
		try{ 
			setCouponInvalid();
		}catch(Exception e){
			e.printStackTrace();
			log.warn("------------"+"自动买家没有及时确认收货异常");
		}
	}
	/**
	 * 用户拥有的优惠券设置为无效
	 */
	public void setCouponInvalid(){
		String cruentDate=DateUtil.getCurrentDateStr();
		System.out.println(cruentDate);
		List<UserCoupon> userCoupons=userCouponMapper.findByEndtime(cruentDate);
		for(UserCoupon userCoupon:userCoupons){
			if(userCoupon!=null){
				userCoupon.setExtend1("0");
				userCouponMapper.updateByPrimaryKeySelective(userCoupon);
			}
		}
	}
	
}
