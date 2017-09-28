package vend.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import base.util.Const;
import base.util.DateUtil;
import base.util.Function;
import vend.entity.VendGoods;
import vend.entity.VendMachine;
import vend.entity.VendOrder;
import vend.entity.VendQrcodeAttend;
import vend.entity.VendRole;
import vend.entity.VendSyslog;
import vend.entity.VendUser;
import vend.service.VendGoodsService;
import vend.service.VendMachineService;
import vend.service.VendOrderService;
import vend.service.VendQrcodeAttendService;
import vend.service.VendRoleService;
import vend.service.VendSyslogService;
import vend.service.VendUserService;

@Controller
@RequestMapping("/mobile")
public class MobileController {
	public static Logger logger = Logger.getLogger(MobileController.class);
	@Autowired
	VendUserService vendUserService;
	@Autowired
	VendSyslogService vendSyslogService;
	@Autowired
	VendRoleService vendRoleService;
	@Autowired
	VendGoodsService vendGoodsService;
	@Autowired
	VendMachineService vendMachineService;
	@Autowired
	VendOrderService vendOrderService;
	@Autowired
	VendQrcodeAttendService vendQrcodeAttendService;
	/**
	 * 跳转消费用户手机登录
	 * @return
	 */
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String mobileLogin(){
        return "../../mobile/login";
    }
    /**
     * 消费用户手机登录
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) throws Exception{
		Subject subject=SecurityUtils.getSubject();
		String username = request.getParameter("userName");
		String password =Function.getEncrypt(request.getParameter("password"));
		
		if("".equals(username)){
			 model.addAttribute("erroruserName", "请填写用户名");
			 return "login";
		}
		if("".equals(password)){
			 model.addAttribute("errorpassword", "请填写密码");
			 return "login";
		}
		VendUser user=vendUserService.selectByUsername(username);
		if(user==null){
			model.addAttribute("erroruserName", "用户名不正确");
			return "login";
		}
		if(!user.getPassword().equals(password)){
			model.addAttribute("errorpassword", "密码不正确");
			return "login";
		}
		if (subject.isAuthenticated()) {  
            return "../../mobile/goods_list";  
        }  
		//logger.info(userService.getByUserName(username).getUserName() + "+" + password);
		
		UsernamePasswordToken token=new UsernamePasswordToken(username, password);
		
		subject.login(token);
		//try{
			//subject.login(token);
		//}catch(Exception e){
		 //   model.addAttribute("errorMsg", "用户名或密码错误");
		 //   return "login";
		//}
		VendUser vendUser = vendUserService.selectByUsername(username);
		/**
		 * 登录日志
		 */
		VendSyslog vendSyslog=new VendSyslog();
		vendSyslog.setUsercode(vendUser.getUsercode());
		vendSyslog.setUsername(username);
		vendSyslog.setExtend1("");
		vendSyslog.setOperIp(request.getRemoteAddr());
		if(vendUser.getRoleId()!=null){
			VendRole vendRole=vendRoleService.getOne(vendUser.getRoleId());
			if(vendRole!=null&&vendRole.getRoleName()!=null){
				vendSyslog.setExtend1(vendRole.getRoleName());
			}
		}
		vendSyslog.setOperDescription("登录成功");
		vendSyslogService.insertVendSyslog(vendSyslog);
		
		subject.getSession().setAttribute("vendUser", vendUser);
		return "../../mobile/goods_list";
	}
    /**
     * 免费商品列表
     * @param model
     * @param wechatpubNo
     * @return
     */
    @RequestMapping(value="/{wechatpubNo}/goodss",method=RequestMethod.GET)
    public String freeGoodss(Model model,@PathVariable String wechatpubNo){
    	List<VendGoods> vendGoodss=vendGoodsService.findAll();
    	model.addAttribute("wechatpubNo", wechatpubNo);
    	model.addAttribute("vendGoodss", vendGoodss);
    	return "../../mobile/goods_list";
    }
    /**
     * 跳转商品详情
     * @param model
     * @return
     */
    @RequestMapping(value="/{id}/detail",method=RequestMethod.GET)
    public String goodsDetail(Model model,@PathVariable int id){
    	VendGoods vendGoods=vendGoodsService.getOne(id);
    	model.addAttribute("vendGoods", vendGoods);
    	return "../../mobile/goods_detail";
    }
    /**
     * 免费获取商品
     * @param response
     * @param map
     * @return
     * @throws IOException
     */
	@RequestMapping(value="/freePay",method=RequestMethod.POST)
	public String freePay(HttpServletResponse response,@RequestBody Map<String, String> map) throws IOException{
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
		/**售卖指令*/
		if(vendOrder.getMachineCode()!=null){
			VendGoods vendGoods=vendGoodsService.getOne(vendOrder.getGoodsId());
			if(vendMachine!=null&&vendGoods!=null){
				vendGoodsService.sellGoods(vendMachine, vendGoods, vendOrder);
			}
		}
		/**售卖指令*/
		
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
		vendQrcodeAttend.setExend1(wechatusercode);
		vendQrcodeAttendService.insertVendQrcodeAttend(vendQrcodeAttend);
		
		try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
