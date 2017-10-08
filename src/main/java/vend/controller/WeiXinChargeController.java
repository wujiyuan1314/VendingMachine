package vend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;

import base.util.DateUtil;
import base.util.Function;
import base.util.HttpClientUtil;
import base.util.JsonUtil;
import base.weixinpay.common.Configure;
import base.weixinpay.common.HttpRequest;
import base.weixinpay.common.RandomStringGenerator;
import base.weixinpay.common.Signature;
import base.weixinpay.common.StreamUtil;
import base.weixinpay.model.OrderInfo;
import base.weixinpay.model.OrderReturnInfo;
import base.weixinpay.model.SignInfo;
import vend.entity.VendAccount;
import vend.entity.VendAccountDetail;
import vend.entity.VendCoupon;
import vend.entity.VendOrder;
import vend.service.VendAccountDetailService;
import vend.service.VendAccountService;
import vend.service.VendCouponService;
import vend.service.VendMachineService;
import vend.service.VendOrderService;
import vend.service.VendParaService;
import vend.service.VendUserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@Controller
@RequestMapping("/wxcharge")
public class WeiXinChargeController {
	public static Logger logger = Logger.getLogger(WeiXinChargeController.class);
	@Autowired
	VendParaService vendParaService;
	@Autowired
	VendOrderService vendOrderService;
	@Autowired
	VendUserService vendUserService;
	@Autowired
	VendMachineService vendMachineService;
	@Autowired
	VendAccountService vendAccountService;
	@Autowired
	VendCouponService vendCouponService;
	@Autowired
	VendAccountDetailService vendAccountDetailService;
	/**
	 * 得到本次充值的openid
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getsessionkey",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, String> getSessionKey(@RequestBody Map<String, String> map){
		logger.info("----------------------进入getsessionkey------------------------");
		Configure.setAppID(vendParaService.selectByParaCode("appid"));
		Configure.setMch_id(vendParaService.selectByParaCode("mch_id"));
		Configure.setKey(vendParaService.selectByParaCode("key"));
		Configure.setSecret(vendParaService.selectByParaCode("appsecret"));
		String uri="https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid="+Configure.getAppID()+"&secret="+Configure.getSecret()+""
				+ "&js_code="+map.get("code")+""
				+ "&grant_type=authorization_code";
		logger.info("----------------------getsessionkey方法uri------------------------"+uri);
		String json=HttpClientUtil.httpPostRequest(uri);
		Map<String, Object> resultMap=JsonUtil.getMap4Json(json);
		logger.info("----------------------getsessionkey方法resultMap------------------------"+resultMap);
		Map<String, String> returnMap=new HashMap<String, String>();
		returnMap.put("session_key", (String)resultMap.get("session_key"));
		returnMap.put("expires_in", resultMap.get("expires_in").toString());
		returnMap.put("openid", (String)resultMap.get("openid"));
		return returnMap;
	}
	/**
	 * 微信小程序下单
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getorder",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void getOrder(HttpServletRequest request,HttpServletResponse response){
		String openid=request.getParameter("openid");
		String usercode=request.getParameter("usercode");
		double chargeamount=Function.getDouble(request.getParameter("chargeamount"), 0.00);
		
		VendOrder vendOrder=new VendOrder();
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendOrder.setAmount(BigDecimal.valueOf(chargeamount));
		String orderId=Function.getOrderId();
		vendOrder.setOrderId(orderId);
		vendOrder.setUsercode(usercode);
		vendOrder.setShopusercode("");
		vendOrder.setGoodsId(0);
		vendOrder.setMachineCode("");
		vendOrder.setNum(1);
		vendOrder.setCreateTime(createTime);
		vendOrder.setOrderstate("0");
		vendOrder.setExtend1("2");//充值
		vendOrder.setPayType("微信充值");
		vendOrderService.insertVendOrder(vendOrder);
		
		int fee=(int)(chargeamount*100);
		try {
			OrderInfo order = new OrderInfo();
			//设置微信充值的参数
			Configure.setAppID(vendParaService.selectByParaCode("appid"));
			Configure.setMch_id(vendParaService.selectByParaCode("mch_id"));
			Configure.setKey(vendParaService.selectByParaCode("key"));
			Configure.setSecret(vendParaService.selectByParaCode("appsecret"));
			
			order.setAppid(Configure.getAppID());
			order.setMch_id(Configure.getMch_id());
			order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
			order.setBody("微信充值");
			order.setOut_trade_no(orderId);
			order.setTotal_fee(fee);
			order.setSpbill_create_ip("47.93.149.91");
			order.setNotify_url("https://zdypx.benbenniaokeji.com/wxcharge/payresult");
			order.setTrade_type("JSAPI");
			order.setOpenid(openid);
			order.setSign_type("MD5");
			//生成签名
			String sign = Signature.getSign(order);
			order.setSign(sign);
			
			
			String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
			System.out.println(result);
			logger.info("---------下单返回:"+result);
			XStream xStream = new XStream();
			xStream.alias("xml", OrderReturnInfo.class); 

			OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
			JSONObject json = new JSONObject();
			json.put("prepay_id", returnInfo.getPrepay_id());
			json.put("orderId", orderId);
			response.getWriter().append(json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("-------", e);
		}
	}
	/**
	 * 微信小程序充值获取签名
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/sign",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void getSign(HttpServletRequest request,HttpServletResponse response){
		try {
			String repay_id = request.getParameter("repay_id");
			SignInfo signInfo = new SignInfo();
			signInfo.setAppId(Configure.getAppID());
			long time = System.currentTimeMillis()/1000;
			signInfo.setTimeStamp(String.valueOf(time));
			signInfo.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
			signInfo.setRepay_id("prepay_id="+repay_id);
			signInfo.setSignType("MD5");
			//生成签名
			String sign = Signature.getSign(signInfo);
			
			JSONObject json = new JSONObject();
			json.put("timeStamp", signInfo.getTimeStamp());
			json.put("nonceStr", signInfo.getNonceStr());
			json.put("package", signInfo.getRepay_id());
			json.put("signType", signInfo.getSignType());
			json.put("paySign", sign);
			logger.info("-------再签名:"+json.toJSONString());
			response.getWriter().append(json.toJSONString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("-------", e);
		}
	}
	/**
	 * 充值返回结果
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/payresult",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void PayResult(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String reqParams = StreamUtil.read(request.getInputStream());
		logger.info("-------充值结果:"+reqParams);
		StringBuffer sb = new StringBuffer("<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>");
		response.getWriter().append(sb.toString());
	}
	/**
	 * 充值成功处理
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/paysuccess",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void PaySuccess(HttpServletRequest request){
		String requestPayment = request.getParameter("requestPayment");
		String orderId = request.getParameter("orderId");
		logger.info("-------充值结果:"+requestPayment);
		if(requestPayment.equals("requestPayment:ok")){
			//1,修改订单
			VendOrder vendOrder=vendOrderService.getOne(orderId);
			if(vendOrder!=null){
				vendOrder.setOrderstate("1");
				vendOrderService.editVendOrder(vendOrder);
			}
			//2,修改账户
			/**充值活动查询*/
			String currentDate=DateUtil.getCurrentDateStr();
			List<VendCoupon> rechargecoupons=vendCouponService.selectRecharge(currentDate);
			double rechargrbl=0;//充值优惠比例
			if(rechargecoupons.size()>0){
				rechargrbl=rechargecoupons.get(0).getCouponScale().doubleValue();
			}
			/**消费用户账户*/
			Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
			VendAccount vendAccount=vendAccountService.getOne(vendOrder.getUsercode());//商户账户
			double orderamount=vendOrder.getAmount().doubleValue();//订单金额
			double yuamount=orderamount+orderamount*rechargrbl;//充值优惠后的金额
			double amountpre1=vendAccount.getOwnAmount().doubleValue();
			BigDecimal totalamount=BigDecimal.valueOf(yuamount+amountpre1);
			vendAccount.setOwnAmount(totalamount);
			String moneyencrypt=Function.getEncrypt(BigDecimal.valueOf(orderamount+amountpre1).toString());
			vendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt));
			vendAccount.setUpdateTime(updateTime);
			vendAccountService.editVendAccount(vendAccount);
			
			VendAccountDetail vendAccountDetail1=new VendAccountDetail();
			vendAccountDetail1.setUsercode(vendOrder.getUsercode());
			vendAccountDetail1.setAmount(BigDecimal.valueOf(orderamount));
			vendAccountDetail1.setType("1");//充值
			vendAccountDetail1.setCreateTime(updateTime);
			vendAccountDetailService.insertVendAccountDetail(vendAccountDetail1);
			
		}
	}
	/**
	 * 微信小程序提现
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/draw",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void toDraw(HttpServletRequest request,HttpServletResponse response){
		Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
		JSONObject json = new JSONObject();
		json.put("success", "0");
		json.put("msg", "提现失败");
		String usercode = request.getParameter("usercode");
	    String drawamount = request.getParameter("drawamount");
	    VendAccount vendAccount=vendAccountService.getOne(usercode);//商户账户
	    if(vendAccount!=null){
	    	double orderamount=vendAccount.getOwnAmount().doubleValue();//账户余额
			double drawamount1=Double.valueOf(drawamount);//提现金额
			if(drawamount1*100>orderamount*100){
				json.put("success", "0");
				json.put("msg", "提现金额不能大于账户余额");
			}else{
				//BigDecimal totalamount=BigDecimal.valueOf(orderamount-drawamount1);
				//vendAccount.setOwnAmount(totalamount);
				//vendAccount.setUpdateTime(updateTime);
				//int isOk=vendAccountService.editVendAccount(vendAccount);
				
				VendAccountDetail vendAccountDetail1=new VendAccountDetail();
				vendAccountDetail1.setUsercode(usercode);
				vendAccountDetail1.setAmount(BigDecimal.valueOf(drawamount1));
				vendAccountDetail1.setType("2");//提现
				vendAccountDetail1.setCreateTime(updateTime);
				vendAccountDetailService.insertVendAccountDetail(vendAccountDetail1);
				//if(isOk==1){
					json.put("success", "1");
					json.put("msg", "操作成功");
				//}
			}
	    }
	    try {
	    	response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
