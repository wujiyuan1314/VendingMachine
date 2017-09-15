package base.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import base.util.DateUtil;
import vend.dao.UserCouponMapper;
import vend.entity.UserCoupon;
/**
 * 用户优惠券定时任务
 * @author ylsoft
 *
 */
@Component
public class UserCouponTask {
	private static Logger logger = Logger.getLogger(UserCouponTask.class.getName());
	@Autowired
	UserCouponMapper userCouponMapper;
	/**
	 * 用户拥有的优惠券设置为无效
	 */
	@Scheduled(cron="30 0 0 * * ?") //每天24:00:30执行
	public void setCouponInvalid(){
		String cruentDate=DateUtil.getCurrentDateStr();
		List<UserCoupon> userCoupons=userCouponMapper.findByEndtime(cruentDate);
		logger.info(userCoupons.size());
		for(UserCoupon userCoupon:userCoupons){
			if(userCoupon!=null){
				userCoupon.setExtend1("0");
				userCouponMapper.updateByPrimaryKeySelective(userCoupon);
			}
		}
	}
	
}
