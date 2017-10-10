package vend.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import vend.entity.UserCoupon;
import vend.entity.VendAccount;
import vend.entity.VendAccountDetail;
import vend.entity.VendGoods;
import vend.entity.VendMachine;
import vend.entity.VendOrder;
import vend.entity.VendQrcodeAttend;
import vend.entity.VendUser;
import vend.service.UserCouponService;
import vend.service.VendAccountDetailService;
import vend.service.VendAccountService;
import vend.service.VendGoodsService;
import vend.service.VendMachineService;
import vend.service.VendOrderService;
import vend.service.VendParaService;
import vend.service.VendQrcodeAttendService;
import vend.service.VendUserService;

@Controller
@RequestMapping("/wxpay")
public class WeiXinPayController {
	public static Logger logger = Logger.getLogger(WeiXinPayController.class);
	@Autowired
	VendParaService vendParaService;
	@Autowired
	VendOrderService vendOrderService;
	@Autowired
	VendGoodsService vendGoodsService;
	@Autowired
	VendUserService vendUserService;
	@Autowired
	UserCouponService userCouponService;
	@Autowired
	VendMachineService vendMachineService;
	@Autowired
	VendAccountService vendAccountService;
	@Autowired
	VendAccountDetailService vendAccountDetailService;
	@Autowired
	VendQrcodeAttendService vendQrcodeAttendService;
	/**
	 * 得到本次支付的openid
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
		logger.info("----------------------结束getsessionkey------------------------");
		return returnMap;
	}
	/**
	 * 微信小程序下单
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getorder",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String getOrder(HttpServletRequest request,HttpServletResponse response){
		logger.info("----------------------进入getOrder------------------------");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		String openid=request.getParameter("openid");
		String name=request.getParameter("name");
		String heat=request.getParameter("heat");//加热情况
		String usercouponId=request.getParameter("usercouponId");//用户优惠券
		int id=Function.getInt(request.getParameter("id"),0);
		String machinecode=request.getParameter("machinecode");
		VendMachine vendMachine=vendMachineService.selectByMachineCode(machinecode);
		if(vendMachine==null){
			json.put("success", "0");
			json.put("msg", "机器码不存在");
			json.put("prepay_id", "");
			json.put("orderId", "");
			try {
				response.getWriter().append(json.toJSONString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		String shopusercode=vendMachine.getUsercode();
		
		String usercode=request.getParameter("usercode");
		double price=Function.getDouble(request.getParameter("price"), 0.00);
		
		VendOrder vendOrder=new VendOrder();
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendOrder.setAmount(BigDecimal.valueOf(price));
		String orderId=Function.getOrderId();
		vendOrder.setOrderId(orderId);
		if(usercouponId!=null||!usercouponId.equals("")){
			vendOrder.setCouponId(usercouponId);
		}
		vendOrder.setUsercode(usercode);
		vendOrder.setHeat(heat);
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
			order.setSpbill_create_ip("47.93.149.91");
			order.setNotify_url("https://zdypx.benbenniaokeji.com/wxpay/payresult");
			order.setTrade_type("JSAPI");
			order.setOpenid(openid);
			order.setSign_type("MD5");
			//生成签名
			String sign = Signature.getSign(order);
			order.setSign(sign);
			
			
			String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
			logger.info("----------------------getOrder方法------------------------"+result);
			System.out.println(result);
			logger.info("---------下单返回:"+result);
			XStream xStream = new XStream();
			xStream.alias("xml", OrderReturnInfo.class); 

			OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
			json.put("success", "1");
			json.put("msg", "成功");
			json.put("prepay_id", returnInfo.getPrepay_id());
			json.put("orderId", orderId);
			response.getWriter().append(json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("-------", e);
		}
		logger.info("----------------------结束getOrder------------------------");
		return null;
	}
	/**
	 * 免费获取商品
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/freePay",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String freePay(HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		json.put("success", "0");
		json.put("msg", "购买失败");
		
		int id=Function.getInt(map.get("id"),0);//商品ID
		String machinecode=map.get("machinecode");//机器码
		
		String wechatpubNo=map.get("wechatpubNo");//微信公众号的号码
		VendUser vendUser=vendUserService.selectByWechatpubNo(wechatpubNo);
		String wechatusercode="";
		if(vendUser!=null){
			wechatusercode=vendUser.getUsercode();//被关注二维码的商家
		}
		
		VendMachine vendMachine=vendMachineService.selectByMachineCode(machinecode);
		String shopusercode="";//购买的商品所属的商家
		if(vendMachine!=null){
			shopusercode=vendMachine.getUsercode();
		}
		String usercode=map.get("usercode");
		//1,订单操作
		VendOrder vendOrder=new VendOrder();
		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		vendOrder.setAmount(BigDecimal.valueOf(0.00));
		String orderId=Function.getOrderId();
		vendOrder.setOrderId(orderId);
		vendOrder.setUsercode(usercode);
		vendOrder.setShopusercode(shopusercode);
		vendOrder.setGoodsId(id);
		vendOrder.setMachineCode(machinecode);
		vendOrder.setNum(1);
		vendOrder.setCreateTime(createTime);
		vendOrder.setOrderstate("1");
		vendOrder.setFreeStatus("1");//2优惠券代替支付免费，1关注二维码免费，0不免费支付
		vendOrder.setExtend1("1");//购买
		vendOrder.setPayType("扫描二维码免费领取");
		int isOk=vendOrderService.insertVendOrder(vendOrder);
		if(isOk==1){
			json.put("success", 1);
			json.put("msg", "购买成功");
		}
		
		//2,二维码关注操作
		VendQrcodeAttend vendQrcodeAttend=new VendQrcodeAttend();
		vendQrcodeAttend.setAttendTime(createTime);
		vendQrcodeAttend.setUsercode(usercode);
		vendQrcodeAttend.setExtend1(wechatusercode);
		vendQrcodeAttendService.insertVendQrcodeAttend(vendQrcodeAttend);
		
		try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 使用优惠券支付
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/couponPay",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String couponPay(HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		json.put("success", "0");
		json.put("msg", "购买失败");
		
		int usercouponId=Function.getInt(map.get("usercouponId"),0);//该用户所拥有的优惠券ID
		int id=Function.getInt(map.get("id"),0);//商品ID
		String startTime=map.get("startTime");//优惠券开始时间
		String endTime=map.get("endTime");//优惠券结束时间
		Date nowDate=DateUtil.getCurrentDate();//当前日期
		int heat = Function.getInt(map.get("heat"),0);//加热情况，0不加热，1加热
		
		String machinecode=map.get("machinecode");
		VendMachine vendMachine=vendMachineService.selectByMachineCode(machinecode);
		if(vendMachine==null){
			json.put("success", "0");
			json.put("msg", "机器码不存在");
			response.getWriter().append(json.toJSONString());
			return null;
		}
		String shopusercode=vendMachine.getUsercode();
		
		if(DateUtil.daysBetweenForDay(DateUtil.parseDate(startTime), nowDate)<0){
			json.put("success", "0");
			json.put("msg", "还没到优惠开始日期");
			response.getWriter().append(json.toJSONString());
			return null;
		}
		
		if(DateUtil.daysBetweenForDay(nowDate,DateUtil.parseDate(endTime))<0){
			json.put("success", "0");
			json.put("msg", "优惠已结束");
			response.getWriter().append(json.toJSONString());
			return null;
		}
		
		
		
		double couponAmount=Function.getDouble(map.get("couponAmount"),0.0);//优惠券金额
		double price=Function.getDouble(map.get("price"),0.0);//商品金额
		
		if(couponAmount>=price){//优惠券金额大于商品金额
			String usercode=map.get("usercode");
			//1,订单操作
			VendOrder vendOrder=new VendOrder();
			Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
			vendOrder.setAmount(BigDecimal.valueOf(0.00));
			String orderId=Function.getOrderId();
			vendOrder.setOrderId(orderId);
			vendOrder.setCouponId(map.get("usercouponId"));
			vendOrder.setUsercode(usercode);
			vendOrder.setShopusercode(shopusercode);
			vendOrder.setGoodsId(id);
			vendOrder.setMachineCode(machinecode);
			vendOrder.setNum(1);
			vendOrder.setCreateTime(createTime);
			vendOrder.setOrderstate("1");
			vendOrder.setFreeStatus("2");//2优惠券代替支付免费，1关注二维码免费，0不免费支付
			vendOrder.setExtend1("1");//购买
			vendOrder.setPayType("优惠券支付");
			int isOk=vendOrderService.insertVendOrder(vendOrder);
			if(isOk==1){
				json.put("success", 1);
				json.put("msg", "购买成功");
				
				/**售卖指令*/
				if(machinecode!=null){
					VendGoods vendGoods=vendGoodsService.getOne(id);
					if(vendMachine!=null&&vendGoods!=null){
						vendGoodsService.sellGoods(vendMachine, vendGoods, vendOrder,heat);
					}
				}
				/**售卖指令*/
				
				UserCoupon userCoupon=userCouponService.getOne(usercouponId);
				userCoupon.setExtend1("0");//优惠券使用后设置为无效
				userCouponService.editUserCoupon(userCoupon);
			}
		}else{
			UserCoupon userCoupon=userCouponService.getOne(Function.getInt(usercouponId, 0));
			userCoupon.setExtend1("0");//优惠券使用后设置为无效
			userCouponService.editUserCoupon(userCoupon);
			
			String isuseye=map.get("isuseye");//是否使用余额支付
			if(isuseye.equals("1")){
				json.put("success", 2);
				json.put("lastamount", (price-couponAmount));
				json.put("msg", "剩余的使用余额支付");
			}else if(isuseye.equals("0")){
				json.put("success", 3);
				json.put("lastamount", (price-couponAmount));
				json.put("msg", "剩余的使用微信在线支付");
			}
		}
		
		try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 使用余额支付
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/banacePay",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String banacePay(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		json.put("success", "0");
		json.put("msg", "购买失败");
		
		int id=Function.getInt(request.getParameter("id"),0);
		String usercouponId=request.getParameter("usercouponId");//用户所拥有的优惠券ID
		String machinecode=request.getParameter("machinecode");
		VendMachine vendMachine=vendMachineService.selectByMachineCode(machinecode);
		if(vendMachine==null){
			json.put("success", "0");
			json.put("msg", "机器码不存在");
			response.getWriter().append(json.toJSONString());
			return null;
		}
		String shopusercode=vendMachine.getUsercode();
		
		int heat = Function.getInt(request.getParameter("heat"),0);//加热情况，0不加热，1加热
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
		vendOrder.setFreeStatus("0");//2优惠券代替支付免费，1关注二维码免费，0不免费支付
		vendOrder.setExtend1("1");//购买
		vendOrder.setPayType("余额支付");
		int isOk=vendOrderService.insertVendOrder(vendOrder);
		if(isOk==1){
			/**售卖指令*/
			if(machinecode!=null){
				VendGoods vendGoods=vendGoodsService.getOne(id);
				if(vendMachine!=null&&vendGoods!=null){
					vendGoodsService.sellGoods(vendMachine, vendGoods, vendOrder,heat);
				}
			}
			/**售卖指令*/
		}
		
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
		VendGoods vendGoods=vendGoodsService.getOne(id);
		if(vendGoods!=null){
			xvendAccountDetail.setExtend1(vendGoods.getGoodsName());//购买商品名	
		}
		xvendAccountDetail.setCreateTime(updateTime);
		vendAccountDetailService.insertVendAccountDetail(xvendAccountDetail);
		
		if(!usercouponId.equals("")){
			UserCoupon userCoupon=userCouponService.getOne(Function.getInt(usercouponId, 0));
			userCoupon.setExtend1("0");//优惠券使用后设置为无效
			userCouponService.editUserCoupon(userCoupon);
		}
		
		/**商家账户*/
		VendAccount vendAccount=vendAccountService.getOne(shopusercode);//商户账户
		if(vendAccount!=null){
			VendUser vendUser=vendUserService.getOne(shopusercode);
			double orderamount=price;//订单金额
			double zamount=0.00;//总后台分配金额
			String lrbl=vendUser.getExtend4();
			double lrbl1=0.40;//商家利润比例
			double lrbl2=0.30;//代理后台用户比例
			double lrbl3=0.30;//总后台用户比例
			if(lrbl!=null){
				String lrblarray[]=Function.stringSpilit(lrbl, ":");
				if(lrblarray.length==3){
					lrbl1=Double.valueOf(lrblarray[0])/10;
					lrbl2=Double.valueOf(lrblarray[1])/10;
					lrbl3=Double.valueOf(lrblarray[2])/10;
				}
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
			json.put("msg", "购买成功");
		}else{
			json.put("success", "0");
			json.put("msg", "购买失败");
		}	
		response.getWriter().append(json.toJSONString());
		return null;
	}
	/**
	 * 微信小程序支付获取签名
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/sign",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void getSign(HttpServletRequest request,HttpServletResponse response){
		logger.info("----------------------开始getSign------------------------");
		try {
			String repay_id = request.getParameter("repay_id");
			SignInfo signInfo = new SignInfo();
			signInfo.setAppId(Configure.getAppID());
			long time = System.currentTimeMillis()/1000;
			signInfo.setTimeStamp(String.valueOf(time));
			signInfo.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
			signInfo.setRepay_id("prepay_id="+repay_id);
			signInfo.setSignType("MD5");
			logger.info("----------------------getSign方法signInfo------------------------"+signInfo);
			//生成签名
			String sign = Signature.getSign(signInfo);
			logger.info("----------------------getSign方法sign------------------------"+sign);
			
			JSONObject json = new JSONObject();
			json.put("timeStamp", signInfo.getTimeStamp());
			json.put("nonceStr", signInfo.getNonceStr());
			json.put("package", signInfo.getRepay_id());
			json.put("signType", signInfo.getSignType());
			json.put("paySign", sign);
			logger.info("----------------------getSign方法json------------------------"+json.toJSONString());
			logger.info("-------再签名:"+json.toJSONString());
			response.getWriter().append(json.toJSONString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("-------", e);
		}
		logger.info("----------------------结束getSign------------------------");
	}
	/**
	 * 支付返回结果
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/payresult",method=RequestMethod.GET,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void PayResult(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String reqParams = StreamUtil.read(request.getInputStream());
		Map<String,Object> map=JsonUtil.getMap4Json(reqParams);
		String return_code=map.get("return_code").toString();
		logger.info("-------支付结果:"+reqParams);
		if(return_code.equals("SUCCESS")){
			String orderId=map.get("out_trade_no").toString();
			VendOrder vendOrder=vendOrderService.getOne(orderId);
			/**售卖指令*/
			int heat=0;
			if(vendOrder.getHeat()!=null){
				heat=Function.getInt(vendOrder.getHeat(), 0);
			}
			if(vendOrder.getMachineCode()!=null){
				VendMachine vendMachine=vendMachineService.selectByMachineCode(vendOrder.getMachineCode());
				VendGoods vendGoods=vendGoodsService.getOne(vendOrder.getGoodsId());
				if(vendMachine!=null&&vendGoods!=null){
					vendGoodsService.sellGoods(vendMachine, vendGoods, vendOrder,heat);
				}
			}
			/**售卖指令*/
			
			/**修改订单*/
			String shopusercode=vendOrder.getShopusercode();//商家账号
			if(vendOrder!=null){
				vendOrder.setOrderstate("1");
				vendOrderService.editVendOrder(vendOrder);
			}
			
			
			if(vendOrder.getCouponId()!=null||!vendOrder.getCouponId().equals("")){
				UserCoupon userCoupon=userCouponService.getOne(Function.getInt(vendOrder.getCouponId(), 0));
				userCoupon.setExtend1("0");//优惠券使用后设置为无效
				userCouponService.editUserCoupon(userCoupon);
			}
			/**修改订单*/
			
			/**修改账户*/
			//商家账户
			Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
			VendAccount vendAccount=vendAccountService.getOne(shopusercode);//商户账户
			VendUser vendUser=vendUserService.getOne(shopusercode);
			double orderamount=vendOrder.getAmount().doubleValue();//订单金额
			double zamount=0.00;//总后台分配金额
			String lrbl=vendUser.getExtend4();
			double lrbl1=0.40;//商家利润比例
			double lrbl2=0.30;//代理后台用户比例
			double lrbl3=0.30;//总后台用户比例
			if(lrbl!=null){
				String lrblarray[]=Function.stringSpilit(lrbl, ":");
				if(lrblarray.length==3){
					lrbl1=Double.valueOf(lrblarray[0])/10;
					lrbl2=Double.valueOf(lrblarray[1])/10;
					lrbl3=Double.valueOf(lrblarray[2])/10;
				}
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
			
			//代理用户账户
			if(vendUser!=null){
				VendAccount pendAccount=vendAccountService.getOne(vendUser.getParentUsercode());//代理用户账户
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
			
			//总后台用户账户
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
			/**修改账户*/
		}
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
		String usercouponId=request.getParameter("usercouponId");//用户所拥有的优惠券ID
		String requestPayment = request.getParameter("requestPayment");
		String orderId = request.getParameter("orderId");
		int heat = Function.getInt(request.getParameter("heat"),0);//加热情况，0不加热，1加热
		logger.info("-------支付结果:"+requestPayment);
		if(requestPayment.equals("requestPayment:ok")){
			VendOrder vendOrder=vendOrderService.getOne(orderId);
			/**售卖指令*/
			if(vendOrder.getMachineCode()!=null){
				VendMachine vendMachine=vendMachineService.selectByMachineCode(vendOrder.getMachineCode());
				VendGoods vendGoods=vendGoodsService.getOne(vendOrder.getGoodsId());
				if(vendMachine!=null&&vendGoods!=null){
					vendGoodsService.sellGoods(vendMachine, vendGoods, vendOrder,heat);
				}
			}
			/**售卖指令*/
			
			/**修改订单*/
			String shopusercode=vendOrder.getShopusercode();//商家账号
			if(vendOrder!=null){
				vendOrder.setOrderstate("1");
				vendOrderService.editVendOrder(vendOrder);
			}
			if(usercouponId!=null||!usercouponId.equals("")){
				UserCoupon userCoupon=userCouponService.getOne(Function.getInt(usercouponId, 0));
				userCoupon.setExtend1("0");//优惠券使用后设置为无效
				userCouponService.editUserCoupon(userCoupon);
			}
			/**修改订单*/
			
			/**修改账户*/
			//商家账户
			Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
			VendAccount vendAccount=vendAccountService.getOne(shopusercode);//商户账户
			VendUser vendUser=vendUserService.getOne(shopusercode);
			double orderamount=vendOrder.getAmount().doubleValue();//订单金额
			double zamount=0.00;//总后台分配金额
			String lrbl=vendUser.getExtend4();
			double lrbl1=0.40;//商家利润比例
			double lrbl2=0.30;//代理后台用户比例
			double lrbl3=0.30;//总后台用户比例
			if(lrbl!=null){
				String lrblarray[]=Function.stringSpilit(lrbl, ":");
				if(lrblarray.length==3){
					lrbl1=Double.valueOf(lrblarray[0])/10;
					lrbl2=Double.valueOf(lrblarray[1])/10;
					lrbl3=Double.valueOf(lrblarray[2])/10;
				}
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
			
			//代理用户账户
			if(vendUser!=null){
				VendAccount pendAccount=vendAccountService.getOne(vendUser.getParentUsercode());//代理用户账户
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
			
			//总后台用户账户
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
			/**修改账户*/
		}
	}
}
