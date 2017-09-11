package vend.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import base.util.DateUtil;
import base.util.Page;
import vend.entity.VendAccount;
import vend.entity.VendAccountDetail;
import vend.entity.VendUser;
import vend.service.VendAccountDetailService;
import vend.service.VendAccountService;

@Controller
@RequestMapping("/account")
public class VendAccountController{
	public static Logger logger = Logger.getLogger(VendAccountController.class);
	
	@Autowired
	VendAccountService vendAccountService;
	@Autowired
	VendAccountDetailService vendAccountDetailService;
	/**
	 * 根据输入信息条件查询用户列表，并分页显示
	 * @param model
	 * @param vendAccount
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/accounts")
	public String listVendAccount(Model model,@ModelAttribute VendAccount vendAccount, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendAccount.toString());
		HttpSession session=request.getSession();
		VendUser user=(VendUser)session.getAttribute("vendUser");
		if(user!=null){
			vendAccount.setUsercode(user.getUsercode());	
		}
		List<VendAccount> vendAccounts = vendAccountService.listVendAccount(vendAccount, page);
		model.addAttribute("vendAccounts",vendAccounts);
		return "manage/account/account_list";
	}
	/**
	 * 得到消费用户订单数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/jaccount",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String,Object> getJson(@RequestBody Map<String, String> map,@ModelAttribute Page page) throws IOException {
		Map<String,Object> resultmap=new HashMap<String,Object>();
		String usercode = map.get("usercode");//用户名
		
		VendAccount vendAccount = vendAccountService.getOne(usercode);
		//List<CodeLibrary> ordertypes =codeLibraryService.selectByCodeNo("ORDERTYPE");
		resultmap.put("vendAccount", vendAccount);
		return resultmap;
	}
	/**
	 * 跳转用户账户添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new VendAccount());
		return "manage/account/account_add";
	}
   /**
    * 添加用户账户
    * @param model
    * @param vendAccount
    * @param br
    * @return
    */
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,@Validated VendAccount vendAccount,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/account/account_add";
    	}
    	vendAccountService.insertVendAccount(vendAccount);
    	return "redirect:accounts";
	}
    /**
	 * 跳转用户修改界面
	 * @param model
	 * @return
	 */
    @RequiresPermissions({"acount:edit"})
	@RequestMapping(value="/{usercode}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable String usercode){
		VendAccount vendAccount=vendAccountService.getOne(usercode);
		model.addAttribute(vendAccount);
		return "manage/account/account_edit";
	}
	/**
	 * 修改用户账户
	 * @param model
	 * @param vendAccount
	 * @param br
	 * @return
	 */
    @RequiresPermissions({"acount:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Model model,@Validated VendAccount vendAccount,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/account/account_edit";
    	}
    	vendAccountService.editVendAccount(vendAccount);
		return "redirect:accounts";
	}
    /**
     * 删除用户账户
     * @param account
     * @param br
     * @return
     */
    @RequiresPermissions({"acount:del"})
    @RequestMapping(value="/{accountcode}/del")
 	public String del(@PathVariable String accountcode){
    	vendAccountService.delVendAccount(accountcode);
 		return "redirect:/account/accounts";
 	}
    /**
     * 批量删除用户账户
     * @param ids
     * @return
     */
    @RequiresPermissions({"acount:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String usercodes=request.getParameter("ids");
    	String usercodeArray[]=usercodes.split(",");
    	vendAccountService.delVendAccounts(usercodeArray);
  		return "redirect:/account/accounts";
  	}
    /**
     * 跳转到提现界面
     * @param model
     * @param usercode
     * @return
     */
    @RequiresPermissions({"account:draw"})
    @RequestMapping(value="/{usercode}/draw",method=RequestMethod.GET)
    public String toDraw(Model model,@PathVariable String usercode){
    	VendAccount vendAccount=vendAccountService.getOne(usercode);
		model.addAttribute(vendAccount);
    	return "manage/account/account_draw";
    }
    /**
     * 商户和代理后台用户提现
     * @param vendAccount
     * @param br
     * @return
     */
    @RequiresPermissions({"account:draw"})
    @RequestMapping(value="/draw",method=RequestMethod.POST)
    public String toDraw(@Validated VendAccount vendAccount,BindingResult br){
    	Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
    	VendAccount pvendAccount=vendAccountService.getOne(vendAccount.getUsercode());
    	if(pvendAccount==null){
    		br.rejectValue("extend1", "NotVendAccount", "没有该账户");
    		if(br.hasErrors()){
	    		return "manage/account/account_draw";
	    	}
    	}else{
    		double orderamount=pvendAccount.getOwnAmount().doubleValue();//账户余额
			double drawamount1=Double.valueOf(vendAccount.getExtend1());//提现金额
			if(drawamount1*100>orderamount*100){
				br.rejectValue("extend1", "NotRepeat", "提现金额大于账户余额");
			}
			if(br.hasErrors()){
	    		return "manage/account/account_draw";
	    	}
			VendAccountDetail vendAccountDetail1=new VendAccountDetail();
			vendAccountDetail1.setUsercode(vendAccount.getUsercode());
			vendAccountDetail1.setAmount(BigDecimal.valueOf(drawamount1));
			vendAccountDetail1.setType("2");//提现
			vendAccountDetail1.setCreateTime(updateTime);
			vendAccountDetailService.insertVendAccountDetail(vendAccountDetail1);
    	}
	    return "redirect:/account/accounts";
    }
}
