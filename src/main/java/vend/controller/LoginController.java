package vend.controller;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends LogoutFilter{
	public static Logger logger = Logger.getLogger(LoginController.class);
	
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
}
