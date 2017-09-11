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
import vend.entity.VendMachine;
import vend.entity.VendOrder;
import vend.entity.VendUser;
import vend.service.VendAccountDetailService;
import vend.service.VendAccountService;
import vend.service.VendMachineService;
import vend.service.VendOrderService;
import vend.service.VendParaService;
import vend.service.VendUserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@Controller
@RequestMapping("/wxpay")
public class WeiXinPayController {
	public static Logger logger = Logger.getLogger(WeiXinPayController.class);
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
	VendAccountDetailService vendAccountDetailService;
	/**
	 * 得到本次支付的openid
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getsessionkey",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, String> getSessionKey(@RequestBody Map<String, String> map){
		Configure.setAppID(vendParaService.selectByParaCode("appid"));
		Configure.setMch_id(vendParaService.selectByParaCode("mch_id"));
		Configure.setKey(vendParaService.selectByParaCode("key"));
		Configure.setSecret(vendParaService.selectByParaCode("appsecret"));
		String uri="https://api.weixin.qq.com/sns/jscode2session?"
				+ "appid="+Configure.getAppID()+"&secret="+Configure.getSecret()+""
				+ "&js_code="+map.get("code")+""
				+ "&grant_type=authorization_code";
		String json=HttpClientUtil.httpPostRequest(uri);
		Map<String, Object> resultMap=JsonUtil.getMap4Json(json);
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
		String name=request.getParameter("name");
		int id=Function.getInt(request.getParameter("id"),0);
		String machinecode=request.getParameter("machinecode");
		VendMachine vendMachine=vendMachineService.selectByMachineCode(machinecode);
		String shopusercode="";
		if(vendMachine!=null){
			shopusercode=vendMachine.getUsercode();
		}
		String usercode=request.getParameter("usercode");
		double price=Function.getDouble(request.getParameter("price"), 0.00);
		
		VendOrder vendOrder=new VendOrder();
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendOrder.setAmount(BigDecimal.valueOf(price));
		String orderId=Function.getOrderId();
		vendOrder.setOrderId(orderId);
		vendOrder.setUsercode(usercode);
		vendOrder.setShopusercode(shopusercode);
		vendOrder.setGoodsId(id);
		vendOrder.setMachineCode(machinecode);
		vendOrder.setNum(1);
		vendOrder.setCreateTime(createTime);
		vendOrder.setOrderstate("0");
		vendOrder.setFreeStatus("0");//是否使用免费券,1使用，0不使用
		vendOrder.setExtend1("1");//购买
		vendOrder.setPayType("微信支付");
		vendOrderService.insertVendOrder(vendOrder);
		
		
		int fee=(int)(price*100);
		try {
			OrderInfo order = new OrderInfo();
			//设置微信支付的参数
			Configure.setAppID(vendParaService.selectByParaCode("appid"));
			Configure.setMch_id(vendParaService.selectByParaCode("mch_id"));
			Configure.setKey(vendParaService.selectByParaCode("key"));
			Configure.setSecret(vendParaService.selectByParaCode("appsecret"));
			
			order.setAppid(Configure.getAppID());
			order.setMch_id(Configure.getMch_id());
			order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
			order.setBody(name);
			order.setOut_trade_no(orderId);
			order.setTotal_fee(fee);
			order.setSpbill_create_ip("1.192.121.236");
			order.setNotify_url("http://www.vm.com/VendingMachine/wxpay/payresult");
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
	 * 使用免费券支付
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/couponPay",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void couponPay(HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		json.put("success", "0");
		
		int id=Function.getInt(request.getParameter("id"),0);
		String machinecode=request.getParameter("machinecode");
		VendMachine vendMachine=vendMachineService.selectByMachineCode(machinecode);
		String shopusercode="";
		if(vendMachine!=null){
			shopusercode=vendMachine.getUsercode();
		}
		String usercode=request.getParameter("usercode");
		double price=0.00;
		//1,订单操作
		VendOrder vendOrder=new VendOrder();
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendOrder.setAmount(BigDecimal.valueOf(price));
		String orderId=Function.getOrderId();
		vendOrder.setOrderId(orderId);
		vendOrder.setUsercode(usercode);
		vendOrder.setShopusercode(shopusercode);
		vendOrder.setGoodsId(id);
		vendOrder.setMachineCode(machinecode);
		vendOrder.setNum(1);
		vendOrder.setCreateTime(createTime);
		vendOrder.setOrderstate("1");
		vendOrder.setFreeStatus("1");//是否使用免费券,1使用，0不使用
		vendOrder.setExtend1("1");//购买
		vendOrder.setPayType("免费券支付");
		int isOk=vendOrderService.insertVendOrder(vendOrder);
		if(isOk==1){
			json.put("success", 1);
		}
		try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 使用余额支付
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/banacePay",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void banacePay(HttpServletRequest request,HttpServletResponse response){
		int id=Function.getInt(request.getParameter("id"),0);
		String machinecode=request.getParameter("machinecode");
		VendMachine vendMachine=vendMachineService.selectByMachineCode(machinecode);
		String shopusercode="";
		if(vendMachine!=null){
			shopusercode=vendMachine.getUsercode();
		}
		String usercode=request.getParameter("usercode");
		double price=Function.getDouble(request.getParameter("price"), 0.00);
		//1,订单操作
		VendOrder vendOrder=new VendOrder();
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendOrder.setAmount(BigDecimal.valueOf(price));
		String orderId=Function.getOrderId();
		vendOrder.setOrderId(orderId);
		vendOrder.setUsercode(usercode);
		vendOrder.setShopusercode(shopusercode);
		vendOrder.setGoodsId(id);
		vendOrder.setMachineCode(machinecode);
		vendOrder.setNum(1);
		vendOrder.setCreateTime(createTime);
		vendOrder.setOrderstate("1");
		vendOrder.setFreeStatus("0");//是否使用免费券,1使用，0不使用
		vendOrder.setExtend1("1");//购买
		vendOrder.setPayType("余额支付");
		vendOrderService.insertVendOrder(vendOrder);
		
		//2,修改账户
		Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
		/**消费用户账户*/
		VendAccount xvendAccount=vendAccountService.getOne(usercode);//消费用户账户
		double xamountpre=xvendAccount.getOwnAmount().doubleValue();
		BigDecimal xtotalamount=BigDecimal.valueOf(xamountpre-price);
		xvendAccount.setOwnAmount(xtotalamount);
		String xmoneyencrypt=Function.getEncrypt(BigDecimal.valueOf(xamountpre-price).toString());
		xvendAccount.setMoneyencrypt(Function.getEncrypt(xmoneyencrypt));
		xvendAccount.setUpdateTime(updateTime);
		vendAccountService.editVendAccount(xvendAccount);
		
		VendAccountDetail xvendAccountDetail=new VendAccountDetail();
		xvendAccountDetail.setUsercode(usercode);
		xvendAccountDetail.setAmount(BigDecimal.valueOf(price));
		xvendAccountDetail.setType("3");//购买
		xvendAccountDetail.setCreateTime(updateTime);
		vendAccountDetailService.insertVendAccountDetail(xvendAccountDetail);
		
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		/**商家账户*/
		VendAccount vendAccount=vendAccountService.getOne(shopusercode);//商户账户
		if(vendAccount!=null){
			VendUser vendUser=vendUserService.getOne(shopusercode);
			double orderamount=price;//订单金额
			double zamount=0.00;//总后台分配金额
			double lrbl1=0.00;
			if(vendUser.getExtend2()!=null){
				lrbl1=Double.valueOf(vendUser.getExtend2())/100;
			}
			double amountnow1=orderamount*lrbl1;
			double amountpre1=vendAccount.getOwnAmount().doubleValue();
			BigDecimal totalamount=BigDecimal.valueOf(amountnow1+amountpre1);
			vendAccount.setOwnAmount(totalamount);
			String moneyencrypt=Function.getEncrypt(BigDecimal.valueOf(amountnow1+amountpre1).toString());
			vendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt));
			vendAccount.setUpdateTime(updateTime);
			vendAccountService.editVendAccount(vendAccount);
			
			VendAccountDetail vendAccountDetail1=new VendAccountDetail();
			vendAccountDetail1.setUsercode(shopusercode);
			vendAccountDetail1.setAmount(BigDecimal.valueOf(amountnow1));
			vendAccountDetail1.setType("3");//购买
			vendAccountDetail1.setCreateTime(updateTime);
			vendAccountDetailService.insertVendAccountDetail(vendAccountDetail1);
			
			/**代理用户账户*/
			if(vendUser!=null){
				VendAccount pendAccount=vendAccountService.getOne(vendUser.getParentUsercode());//代理用户账户
				VendUser pvendUser=vendUserService.getOne(pendAccount.getUsercode());
				double lrbl2=0.00;
				if(pvendUser.getExtend2()!=null){
					lrbl2=Double.valueOf(pvendUser.getExtend2())/100;
				}
				double amountnow2=orderamount*lrbl2;
				zamount=orderamount-amountnow1-amountnow2;
				double amountpre2=vendAccount.getOwnAmount().doubleValue();
				BigDecimal totalamount2=BigDecimal.valueOf(amountnow2+amountpre2);
				pendAccount.setOwnAmount(totalamount2);
				String moneyencrypt2=Function.getEncrypt(BigDecimal.valueOf(amountnow2+amountpre2).toString());
				pendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt2));
				pendAccount.setUpdateTime(updateTime);
				vendAccountService.editVendAccount(pendAccount);
				
				VendAccountDetail vendAccountDetail2=new VendAccountDetail();
				vendAccountDetail2.setUsercode(vendUser.getParentUsercode());
				vendAccountDetail2.setAmount(BigDecimal.valueOf(amountnow2));
				vendAccountDetail2.setType("3");//购买
				vendAccountDetail2.setCreateTime(updateTime);
				vendAccountDetailService.insertVendAccountDetail(vendAccountDetail2);
			}
			
			/**总后台用户账户*/
			VendAccount zendAccount=vendAccountService.getOne("VM001");//总账户
			double amountnow3=zamount;
			double amountpre3=zendAccount.getOwnAmount().doubleValue();
			BigDecimal totalamount3=BigDecimal.valueOf(amountnow3+amountpre3);
			zendAccount.setOwnAmount(totalamount3);
			String moneyencrypt2=Function.getEncrypt(BigDecimal.valueOf(amountnow3+amountpre3).toString());
			zendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt2));
			zendAccount.setUpdateTime(updateTime);
			vendAccountService.editVendAccount(zendAccount);
			
			VendAccountDetail vendAccountDetail3=new VendAccountDetail();
			vendAccountDetail3.setUsercode("VM001");
			vendAccountDetail3.setAmount(BigDecimal.valueOf(amountnow3));
			vendAccountDetail3.setType("3");//购买
			vendAccountDetail3.setCreateTime(updateTime);
			vendAccountDetailService.insertVendAccountDetail(vendAccountDetail3);
			json.put("success", "1");
		}else{
			json.put("success", "0");
		}	
		try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 微信小程序支付获取签名
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
	 * 支付返回结果
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/payresult",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void PayResult(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String reqParams = StreamUtil.read(request.getInputStream());
		logger.info("-------支付结果:"+reqParams);
		StringBuffer sb = new StringBuffer("<xml><return_code>SUCCESS</return_code><return_msg>OK</return_msg></xml>");
		response.getWriter().append(sb.toString());
	}
	/**
	 * 支付成功处理
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/paysuccess",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void PaySuccess(HttpServletRequest request){
		String requestPayment = request.getParameter("requestPayment");
		String orderId = request.getParameter("orderId");
		logger.info("-------支付结果:"+requestPayment);
		if(requestPayment.equals("requestPayment:ok")){
			//1,修改订单
			VendOrder vendOrder=vendOrderService.getOne(orderId);
			String shopusercode=vendOrder.getShopusercode();//商家账号
			if(vendOrder!=null){
				vendOrder.setOrderstate("1");
				vendOrderService.editVendOrder(vendOrder);
			}
			//2,修改账户
			/**商家账户*/
			Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
			VendAccount vendAccount=vendAccountService.getOne(shopusercode);//商户账户
			VendUser vendUser=vendUserService.getOne(shopusercode);
			double orderamount=vendOrder.getAmount().doubleValue();//订单金额
			double zamount=0.00;//总后台分配金额
			double lrbl1=0.00;
			if(vendUser.getExtend2()!=null){
				lrbl1=Double.valueOf(vendUser.getExtend2())/100;
			}
			double amountnow1=orderamount*lrbl1;
			double amountpre1=vendAccount.getOwnAmount().doubleValue();
			BigDecimal totalamount=BigDecimal.valueOf(amountnow1+amountpre1);
			vendAccount.setOwnAmount(totalamount);
			String moneyencrypt=Function.getEncrypt(BigDecimal.valueOf(amountnow1+amountpre1).toString());
			vendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt));
			vendAccount.setUpdateTime(updateTime);
			vendAccountService.editVendAccount(vendAccount);
			
			VendAccountDetail vendAccountDetail1=new VendAccountDetail();
			vendAccountDetail1.setUsercode(shopusercode);
			vendAccountDetail1.setAmount(BigDecimal.valueOf(amountnow1));
			vendAccountDetail1.setType("3");//购买
			vendAccountDetail1.setCreateTime(updateTime);
			vendAccountDetailService.insertVendAccountDetail(vendAccountDetail1);
			
			/**代理用户账户*/
			if(vendUser!=null){
				VendAccount pendAccount=vendAccountService.getOne(vendUser.getParentUsercode());//代理用户账户
				VendUser pvendUser=vendUserService.getOne(pendAccount.getUsercode());
				double lrbl2=0.00;
				if(pvendUser.getExtend2()!=null){
					lrbl2=Double.valueOf(pvendUser.getExtend2())/100;
				}
				double amountnow2=orderamount*lrbl2;
				zamount=orderamount-amountnow1-amountnow2;
				double amountpre2=vendAccount.getOwnAmount().doubleValue();
				BigDecimal totalamount2=BigDecimal.valueOf(amountnow2+amountpre2);
				pendAccount.setOwnAmount(totalamount2);
				String moneyencrypt2=Function.getEncrypt(BigDecimal.valueOf(amountnow2+amountpre2).toString());
				pendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt2));
				pendAccount.setUpdateTime(updateTime);
				vendAccountService.editVendAccount(pendAccount);
				
				VendAccountDetail vendAccountDetail2=new VendAccountDetail();
				vendAccountDetail2.setUsercode(vendUser.getParentUsercode());
				vendAccountDetail2.setAmount(BigDecimal.valueOf(amountnow2));
				vendAccountDetail2.setType("3");//购买
				vendAccountDetail2.setCreateTime(updateTime);
				vendAccountDetailService.insertVendAccountDetail(vendAccountDetail2);
			}
			
			/**总后台用户账户*/
			VendAccount zendAccount=vendAccountService.getOne("VM001");//总账户
			double amountnow3=zamount;
			double amountpre3=zendAccount.getOwnAmount().doubleValue();
			BigDecimal totalamount3=BigDecimal.valueOf(amountnow3+amountpre3);
			zendAccount.setOwnAmount(totalamount3);
			String moneyencrypt2=Function.getEncrypt(BigDecimal.valueOf(amountnow3+amountpre3).toString());
			zendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt2));
			zendAccount.setUpdateTime(updateTime);
			vendAccountService.editVendAccount(zendAccount);
			
			VendAccountDetail vendAccountDetail3=new VendAccountDetail();
			vendAccountDetail3.setUsercode("VM001");
			vendAccountDetail3.setAmount(BigDecimal.valueOf(amountnow3));
			vendAccountDetail3.setType("3");//购买
			vendAccountDetail3.setCreateTime(updateTime);
			vendAccountDetailService.insertVendAccountDetail(vendAccountDetail3);
		}
	}
}
