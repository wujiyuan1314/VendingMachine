package vend.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.util.DateUtil;
import base.util.Function;
import base.util.Page;
import vend.entity.CodeLibrary;
import vend.entity.VendAccount;
import vend.entity.VendAccountDetail;
import vend.entity.VendRole;
import vend.entity.VendUser;
import vend.service.CodeLibraryService;
import vend.service.VendAccountDetailService;
import vend.service.VendAccountService;
import vend.service.VendRoleService;
import vend.service.VendUserService;

@Controller
@RequestMapping("/accountdetail")
public class VendAccountDetailController{
	public static Logger logger = Logger.getLogger(VendAccountDetailController.class);
	
	@Autowired
	VendAccountDetailService vendAccountDetailService;
	@Autowired
	VendUserService vendUserService;
	@Autowired
	VendRoleService vendRoleService;
	@Autowired
	VendAccountService vendAccountService;
	@Autowired
	CodeLibraryService codeLibraryService;
	/**
	 * 根据输入信息条件查询角色账户记录列表，并分页显示
	 * @param model
	 * @param vendAccountDetail
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/accountdetails")
	public String listVendAccountDetail(Model model,@ModelAttribute VendAccountDetail vendAccountDetail, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendAccountDetail.toString());
		List<CodeLibrary> accounttypes=codeLibraryService.selectByCodeNo("ACCOUNTTYPE");
		model.addAttribute("accounttypes", accounttypes);
		List<VendAccountDetail> vendAccountDetails = vendAccountDetailService.listVendAccountDetail(vendAccountDetail, page);
		for(VendAccountDetail vendAccountDetail1:vendAccountDetails){
			VendUser vendUser=vendUserService.getOne(vendAccountDetail1.getUsercode());
			if(vendUser!=null&&vendUser.getUsername()!=null){
				vendAccountDetail1.setUsercode(vendUser.getUsername());
			}
			VendRole vendRole=vendRoleService.getOne(vendUser.getRoleId());
			if(vendRole!=null&&vendRole.getRoleName()!=null){
				vendAccountDetail1.setExtend2(vendRole.getRoleName());
			}
		}
		model.addAttribute("vendAccountDetails",vendAccountDetails);
		return "manage/account/account_detail";
	}
	/**
	 * 提现记录
	 * @param model
	 * @param vendAccountDetail
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/draw")
	public String listDraw(Model model,@ModelAttribute VendAccountDetail vendAccountDetail, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendAccountDetail.toString());
		List<CodeLibrary> accounttypes=codeLibraryService.selectByCodeNo("ACCOUNTTYPE");
		model.addAttribute("accounttypes", accounttypes);
		List<VendAccountDetail> vendAccountDetails = vendAccountDetailService.listVendAccountDetailTx(vendAccountDetail, page);
		List<VendAccountDetail> list=new ArrayList<VendAccountDetail>();
		for(VendAccountDetail vendAccountDetail1:vendAccountDetails){
			if(vendAccountDetail1.getExtend1()==null||"".equals(vendAccountDetail1.getExtend1())){
				VendUser vendUser=vendUserService.getOne(vendAccountDetail1.getUsercode());
				if(vendUser!=null&&vendUser.getUsername()!=null){
					vendAccountDetail1.setUsercode(vendUser.getUsername());
				}
				VendRole vendRole=vendRoleService.getOne(vendUser.getRoleId());
				if(vendRole!=null&&vendRole.getRoleName()!=null){
					vendAccountDetail1.setExtend2(vendRole.getRoleName());
				}
				list.add(vendAccountDetail1);
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("vendAccountDetails",list);
		return "manage/account/account_draw";
	}
	/**
	 * 跳转角色账户记录信息添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new VendAccountDetail());
		return "manage/account/account_add";
	}
   /**
    * 添加角色账户记录信息
    * @param model
    * @param vendAccountDetail
    * @param br
    * @return
    */
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,@Validated VendAccountDetail vendAccountDetail,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/account/account_add";
    	}
    	vendAccountDetailService.insertVendAccountDetail(vendAccountDetail);
    	return "redirect:accounts";
	}
    /**
	 * 跳转角色账户记录修改界面
	 * @param model
	 * @return
	 */
    @RequiresPermissions({"accountdetail:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendAccountDetail vendAccountDetail=vendAccountDetailService.getOne(id);
		model.addAttribute(vendAccountDetail);
		return "manage/account/account_edit";
	}
	/**
	 * 修改角色账户记录信息
	 * @param model
	 * @param vendAccountDetail
	 * @param br
	 * @return
	 */
    @RequiresPermissions({"accountdetail:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Model model,@Validated VendAccountDetail vendAccountDetail,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/account/account_edit";
    	}
    	vendAccountDetailService.editVendAccountDetail(vendAccountDetail);
		return "redirect:accountdetails";
	}
    /**
     * 删除角色账户记录信息
     * @param user
     * @param br
     * @return
     */
    @RequiresPermissions({"accountdetail:del"})
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendAccountDetailService.delVendAccountDetail(id);
 		return "redirect:/accountdetail/accountdetails";
 	}
    /**
     * 批量删除角色账户记录信息
     * @param ids
     * @return
     */
    @RequiresPermissions({"accountdetail:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
    	vendAccountDetailService.delVendAccountDetails(idArray1);
  		return "redirect:/accountdetail/accountdetails";
  	}
    /**
     * 同意提现
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}/agree")
 	public String agree(@PathVariable Integer id){
    	VendAccountDetail vendAccountDetail=vendAccountDetailService.getOne(id);
    	if(vendAccountDetail!=null){
    		vendAccountDetail.setExtend1("已同意提现");
        	vendAccountDetailService.editVendAccountDetail(vendAccountDetail);
        	
        	VendAccount vendAccount=vendAccountService.getOne(vendAccountDetail.getUsercode());
        	double orderamount=vendAccount.getOwnAmount().doubleValue();//账户余额
			double drawamount1=vendAccountDetail.getAmount().doubleValue();//提现金额
			if(drawamount1<=orderamount){
				vendAccount.setOwnAmount(BigDecimal.valueOf(orderamount-drawamount1));
				Date updateTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//更新时间
				vendAccount.setUpdateTime(updateTime);
				String moneyencrypt=Function.getEncrypt(BigDecimal.valueOf(orderamount-drawamount1).toString());
				vendAccount.setMoneyencrypt(Function.getEncrypt(moneyencrypt));
				vendAccountService.editVendAccount(vendAccount);
			}
    	}
 		return "redirect:/accountdetail/draw";
 	}
    /**
     * 拒绝提现
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}/reject")
 	public String reject(@PathVariable Integer id){
    	VendAccountDetail vendAccountDetail=vendAccountDetailService.getOne(id);
    	if(vendAccountDetail!=null){
    		vendAccountDetail.setExtend1("已拒绝提现");
        	vendAccountDetailService.editVendAccountDetail(vendAccountDetail);
    	}
 		return "redirect:/accountdetail/draw";
 	}
}
