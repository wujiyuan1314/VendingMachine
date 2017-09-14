package vend.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import base.util.Const;
import base.util.DateUtil;
import base.util.Function;
import vend.entity.UserCoupon;
import vend.entity.VendAccount;
import vend.entity.VendUser;
import vend.service.UserCouponService;
import vend.service.VendAccountService;
import vend.service.VendUserService;

@Controller
public class LoginController extends LogoutFilter{
	public static Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	VendUserService vendUserService;
	@Autowired
	VendAccountService vendAccountService;
	@Autowired
	UserCouponService userCouponService;
	@RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
		Subject subject=SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {  
            return "redirect:welcome";  
        }else{
        	return "login";
        }
    }
	 
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) throws Exception{
		Subject subject=SecurityUtils.getSubject();
		String username = request.getParameter("userName");
		String password =Function.getEncrypt(request.getParameter("password"));
		String verificode=request.getParameter("verificode");//验证码
		HttpSession session=request.getSession();
		String maskcode=(String)session.getAttribute(Const.SESSION_SECURITY_CODE);
		
		if("".equals(verificode)){
			 model.addAttribute("errorverificode", "请填写验证码");
			 return "login";
		}
		if(!maskcode.equals(verificode)){
			 model.addAttribute("errorverificode", "验证码不匹配");
			 return "login";
		}
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
            return "welcome";  
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
		subject.getSession().setAttribute("vendUser", vendUser);
		return "welcome";
	}
    /**
     * 退出登录
     * @throws Exception 
     */
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(ServletRequest request, ServletResponse response) throws Exception {  
        Subject subject = SecurityUtils.getSubject();  
        if (subject.isAuthenticated()) {  
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存  
        }  
        return "redirect:login";
    }
    /**
     * 从微信小程序注册用户信息
     * @param usercode
     * @return
     */
    @RequestMapping(value="/wxreg",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, String> wxreg(@RequestBody Map<String, String> map){
    	String username=map.get("nickname");
    	VendUser user=vendUserService.selectByUsername(username);
    	Date createtime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
    	if(user==null){
    		user=new VendUser();
    		user.setUsername(username);
    		String password=Function.getEncrypt("123456");
    		user.setPassword(password);
    		user.setAddress(map.get("address"));
    		user.setMobile(map.get("mobile"));
    		user.setRoleId(5);
    		user.setCreateTime(createtime);
    		user.setUpdateTime(createtime);
    		int isOk=vendUserService.insertVendUser(user);
    		if(isOk==1){
    			map.put("success", "1");
    			map.put("msg", "注册成功");
    		}else{
    			map.put("success", "0");
    			map.put("msg", "注册失败");
    		}
    	}else{
    		map.put("success", "1");
			map.put("msg", "已经注册过");
    	}
    	return map;
    }
    /**
     * 微信小程序用户登录
     * @return
     */
    @RequestMapping(value="/wxlogin",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody Map<String, Object> wxlogin(@RequestBody Map<String, String> map){
    	Map<String, Object> resultMap=new HashMap<String, Object>();
    	//存储状态信息
    	resultMap.put("avatarUrl", map.get("avatarUrl"));
    	resultMap.put("nickName", map.get("nickName"));
    	resultMap.put("usercode", "");
    	resultMap.put("success", "0");
    	resultMap.put("msg", "登录错误");
    	
    	String username=map.get("nickName");
    	VendUser venduser=vendUserService.selectByUsername(username);
    	if(venduser==null){
    		resultMap.put("usercode", "");
    		resultMap.put("success", "0");
    		resultMap.put("msg", "您还没注册,请先进行注册");
    	}else{
    		List<UserCoupon> userCoupons=userCouponService.findByUsercode(venduser.getUsercode());
    		VendAccount vendAccount = vendAccountService.getOne(venduser.getUsercode());
    		//List<CodeLibrary> ordertypes =codeLibraryService.selectByCodeNo("ORDERTYPE");
    		resultMap.put("vendAccount", vendAccount);
    		resultMap.put("userCoupons", userCoupons);
    		resultMap.put("usercode", venduser.getUsercode());
    		resultMap.put("success", "1");
    		resultMap.put("msg", "登录成功");
    	}
    	return resultMap;
    }
}
