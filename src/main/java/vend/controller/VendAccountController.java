package vend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import base.util.Page;
import vend.entity.VendAccount;
import vend.service.VendAccountService;

@Controller
@RequestMapping("/account")
public class VendAccountController{
	public static Logger logger = Logger.getLogger(VendAccountController.class);
	
	@Autowired
	VendAccountService vendAccountService;
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
		List<VendAccount> vendAccounts = vendAccountService.listVendAccount(vendAccount, page);
		model.addAttribute("vendAccounts",vendAccounts);
		return "manage/account/account_list";
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
	@RequestMapping(value="/{accountcode}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable String accountcode){
		VendAccount vendAccount=vendAccountService.getOne(accountcode);
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
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Model model,@Validated VendAccount vendAccount,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/account/account_edit";
    	}
    	int isOk=vendAccountService.editVendAccount(vendAccount);
		return "redirect:accounts";
	}
    /**
     * 删除用户账户
     * @param account
     * @param br
     * @return
     */
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
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String accountcodes=request.getParameter("ids");
    	String accountcodeArray[]=accountcodes.split(",");
    	int isOk=vendAccountService.delVendAccounts(accountcodeArray);
  		return "redirect:/account/accounts";
  	}
}
