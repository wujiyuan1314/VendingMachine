package vend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import base.util.Function;
import base.util.Page;
import vend.entity.CodeLibrary;
import vend.entity.VendAccountDetail;
import vend.service.CodeLibraryService;
import vend.service.VendAccountDetailService;

@Controller
@RequestMapping("/accountdetail")
public class VendAccountDetailController{
	public static Logger logger = Logger.getLogger(VendAccountDetailController.class);
	
	@Autowired
	VendAccountDetailService vendAccountDetailService;
	@Autowired
	CodeLibraryService codeLibraryService;
	/**
	 * 根据输入信息条件查询角色列表，并分页显示
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
		model.addAttribute("vendAccountDetails",vendAccountDetails);
		return "manage/account/account_detail";
	}
	/**
	 * 跳转角色信息添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new VendAccountDetail());
		return "manage/account/account_add";
	}
   /**
    * 添加角色信息
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
	 * 跳转角色修改界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendAccountDetail vendAccountDetail=vendAccountDetailService.getOne(id);
		model.addAttribute(vendAccountDetail);
		return "manage/account/account_edit";
	}
	/**
	 * 修改角色信息
	 * @param model
	 * @param vendAccountDetail
	 * @param br
	 * @return
	 */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Model model,@Validated VendAccountDetail vendAccountDetail,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/account/account_edit";
    	}
    	int isOk=vendAccountDetailService.editVendAccountDetail(vendAccountDetail);
		return "redirect:accountdetails";
	}
    /**
     * 删除角色信息
     * @param user
     * @param br
     * @return
     */
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendAccountDetailService.delVendAccountDetail(id);;
 		return "redirect:/accountdetail/accountdetails";
 	}
    /**
     * 批量删除角色信息
     * @param ids
     * @return
     */
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
    	int isOk=vendAccountDetailService.delVendAccountDetails(idArray1);
  		return "redirect:/accountdetail/accountdetails";
  	}
}
