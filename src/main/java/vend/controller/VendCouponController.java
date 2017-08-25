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

import base.util.Function;
import base.util.Page;
import vend.entity.CodeLibrary;
import vend.entity.VendCoupon;
import vend.service.CodeLibraryService;
import vend.service.VendCouponService;

@Controller
@RequestMapping("/ad")
public class VendCouponController{
	public static Logger logger = Logger.getLogger(VendCouponController.class);
	
	@Autowired
	VendCouponService vendCouponService;
	@Autowired
	CodeLibraryService codeLibraryService;
	/**
	 * 根据输入信息条件查询广告列表，并分页显示
	 * @param model
	 * @param VendCoupon
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ads")
	public String listVendCoupon(Model model,@ModelAttribute VendCoupon vendCoupon, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendCoupon.toString());
		List<VendCoupon> vendCoupons = vendCouponService.listVendCoupon(vendCoupon, page);
		model.addAttribute("VendCoupons",vendCoupons);
		return "manage/ad/ad_list";
	}
	/**
	 * 跳转广告信息添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		List<CodeLibrary> adtypes=codeLibraryService.selectByCodeNo("ADTYPE");//广告类型列表
		model.addAttribute("adtypes", adtypes);
		model.addAttribute(new VendCoupon());
		return "manage/ad/ad_add";
	}
   /**
    * 添加广告信息
    * @param request
    * @param model
    * @param VendCoupon
    * @param br
    * @return
    */
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request,Model model,@Validated VendCoupon vendCoupon,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/ad/ad_add";
    	}
    	vendCouponService.insertVendCoupon(vendCoupon);
    	return "redirect:ads";
	}
    /**
	 * 跳转广告修改界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendCoupon vendCoupon=vendCouponService.getOne(id);
		model.addAttribute(vendCoupon);
		return "manage/ad/ad_edit";
	}
	/**
	 * 修改广告信息
	 * @param request
	 * @param model
	 * @param VendCoupon
	 * @param br
	 * @return
	 */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,@Validated VendCoupon vendCoupon,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/ad/ad_edit";
    	}
    	int isOk=vendCouponService.editVendCoupon(vendCoupon);
		return "redirect:ads";
	}
    /**
     * 删除广告信息
     * @param user
     * @param br
     * @return
     */
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendCouponService.delVendCoupon(id);;
 		return "redirect:/ad/ads";
 	}
    /**
     * 批量删除广告信息
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
    	int isOk=vendCouponService.delVendCoupons(idArray1);
  		return "redirect:/ad/ads";
  	}
}
