package base.task;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import base.util.DateUtil;
import base.util.HttpClientUtil;
import net.sf.json.JSONObject;
import vend.dao.UserCouponMapper;
import vend.dao.VendParaMapper;
import vend.entity.CodeLibrary;
import vend.entity.UserCoupon;
import vend.entity.VendPara;
import vend.service.CodeLibraryService;
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
	@Autowired
	VendParaMapper vendParaMapper;
	@Autowired
	CodeLibraryService codeLibraryService;
	/**
	 * 用户拥有的优惠券设置为无效
	 */
	@Scheduled(cron="30 0 0 * * ?") //每天24:00:30执行
	public void setCouponInvalid(){
		String cruentDate=DateUtil.getCurrentDateStr();
		System.out.println(DateUtil.getCurrentDateTimeStr());
		List<UserCoupon> userCoupons=userCouponMapper.findByEndtime(cruentDate);
		logger.info(userCoupons.size());
		for(UserCoupon userCoupon:userCoupons){
			if(userCoupon!=null){
				userCoupon.setExtend1("0");
				userCouponMapper.updateByPrimaryKeySelective(userCoupon);
			}
		}
	}
	/**
	 * 获取公众号的access_token用户调取微信用户信息
	 */
	//@Scheduled(cron="0 0 */2 * * ?") //每两个小时执行一次
	/**public void getAccessToken(){
		System.out.println(DateUtil.getCurrentDateTimeStr()+"是多少");
		List<CodeLibrary> codeLibrarys=codeLibraryService.selectByCodeNo("WECHATPUBNO");
		for(CodeLibrary codeLibrary:codeLibrarys){
			if(codeLibrarys!=null){
				String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
						+ "&appid="+codeLibrary.getItemname()+"&secret="+codeLibrary.getExtend1()+"";
				String retMsg=HttpClientUtil.httpGetRequest(url);
				if(StringUtils.isNotBlank(retMsg)){
					JSONObject retJson = JSONObject.fromObject(retMsg);
					logger.info("---------------公众号"+codeLibrary.getItemno()+"的access_token返回信息----------"+retJson);
					if(retJson.has("access_token")){
						String access_token = retJson.getString("access_token");
						VendPara vendPara=vendParaMapper.selectByParaCode(codeLibrary.getItemno()+"access_token");
						if(vendPara!=null){
							vendPara.setParaContent(access_token);
							vendParaMapper.updateByPrimaryKey(vendPara);
						}
						logger.info("---------------公众号"+codeLibrary.getItemno()+"的access_token----------"+access_token);
					}else{
						String errcode = retJson.getString("errcode");
						String errmsg = retJson.getString("errmsg");
						logger.info("获取公众号"+codeLibrary.getItemno()+"的access_token:-----错误码："+errcode+"-----错误信息:"+errmsg);
					}
				}
			}
		}
		//String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxcf691127925c056b&secret=172485d2efdff5b1662e643ce361b636";
	}**/
	
	
}
