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
import vend.entity.VendMachine;
import vend.entity.VendOrder;
import vend.entity.VendUser;
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
@RequestMapping("/wx")
public class WeiXinController {
	public static Logger logger = Logger.getLogger(WeiXinController.class);
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
		vendOrder.setExtend1("1");//购买
		vendOrder.setPayType("微信支付");
		vendOrderService.insertVendOrder(vendOrder);
		
		int fee=(int)price*100;
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
			order.setNotify_url("http://www.vm.com/VendingMachine/wx/payresult");
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
		if(requestPayment.equals("ok")){
			//1,修改订单
			VendOrder vendOrder=vendOrderService.getOne(orderId);
			String shopusercode=vendOrder.getShopusercode();//商家账号
			if(vendOrder!=null){
				vendOrder.setOrderstate("1");
				vendOrderService.editVendOrder(vendOrder);
			}
			//2,修改账户
			Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
			VendAccount vendAccount=vendAccountService.getOne(shopusercode);//商户账户
			VendUser vendUser=vendUserService.getOne(shopusercode);
			double amountnow1=vendOrder.getAmount().doubleValue()*0.4;
			double amountpre1=vendAccount.getOwnAmount().doubleValue();
			BigDecimal totalamount=BigDecimal.valueOf(amountnow1+amountpre1);
			vendAccount.setOwnAmount(totalamount);
			String moneyencrypt=Function.getEncrypt(BigDecimal.valueOf(amountnow1+amountpre1).toString());
			vendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt));
			vendAccount.setUpdateTime(updateTime);
			vendAccountService.editVendAccount(vendAccount);
			
			if(vendUser!=null){
				VendAccount pendAccount=vendAccountService.getOne(vendUser.getParentUsercode());//代理用户账户
				VendUser pvendUser=vendUserService.getOne(pendAccount.getUsercode());
				double amountnow2=vendOrder.getAmount().doubleValue()*0.2;
				double amountpre2=pendAccount.getOwnAmount().doubleValue();
				BigDecimal totalamount2=BigDecimal.valueOf(amountnow1+amountpre1);
				pendAccount.setOwnAmount(totalamount2);
				String moneyencrypt2=Function.getEncrypt(BigDecimal.valueOf(amountnow2+amountpre2).toString());
				pendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt2));
				pendAccount.setUpdateTime(updateTime);
				vendAccountService.editVendAccount(pendAccount);
			}
			
			VendAccount zendAccount=vendAccountService.getOne("VM001");//总账户
			double amountnow3=vendOrder.getAmount().doubleValue()*0.2;
			double amountpre3=zendAccount.getOwnAmount().doubleValue();
			BigDecimal totalamount3=BigDecimal.valueOf(amountnow1+amountpre1);
			zendAccount.setOwnAmount(totalamount3);
			String moneyencrypt2=Function.getEncrypt(BigDecimal.valueOf(amountnow3+amountpre3).toString());
			zendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt2));
			zendAccount.setUpdateTime(updateTime);
			vendAccountService.editVendAccount(zendAccount);
			
			
		}
	}
}
