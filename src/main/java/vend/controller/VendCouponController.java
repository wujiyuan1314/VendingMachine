package vend.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import base.util.Function;
import base.util.Page;
import vend.entity.CodeLibrary;
import vend.entity.VendCoupon;
import vend.entity.VendOrder;
import vend.service.CodeLibraryService;
import vend.service.VendCouponService;

@Controller
@RequestMapping("/coupon")
public class VendCouponController{
	public static Logger logger = Logger.getLogger(VendCouponController.class);
	
	@Autowired
	VendCouponService vendCouponService;
	@Autowired
	CodeLibraryService codeLibraryService;
	/**
	 * 根据输入信息条件查询优惠券列表，并分页显示
	 * @param model
	 * @param VendCoupon
	 * @param page
	 * @param request
	 * @return
	 */
	@RequiresPermissions({"coupon:coupons"})
	@RequestMapping(value="/coupons")
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
		model.addAttribute("vendCoupons",vendCoupons);
		return "manage/coupon/coupon_list";
	}
	/**
	 * 得到消费用户订单数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/jcoupons",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<VendCoupon> getJson() throws IOException {
		List<VendCoupon> vendCoupons = vendCouponService.findAll();
		for(VendCoupon vendCoupon:vendCoupons){
			vendCoupon.setExtend1(DateUtil.format(vendCoupon.getStartTime()));
			vendCoupon.setExtend2(DateUtil.format(vendCoupon.getEndTime()));
		}
		return vendCoupons;
	}
	/**
	 * 跳转优惠券信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"coupon:add"})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String coupond(Model model){
		model.addAttribute(new VendCoupon());
		return "manage/coupon/coupon_add";
	}
   /**
    * 添加优惠券信息
    * @param vendCoupon
    * @param br
    * @return
    */
	@RequiresPermissions({"coupon:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String coupond(@Validated VendCoupon vendCoupon,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/coupon/coupon_coupond";
    	}
    	vendCouponService.insertVendCoupon(vendCoupon);
    	return "redirect:coupons";
	}
    /**
	 * 跳转优惠券修改界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"coupon:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendCoupon vendCoupon=vendCouponService.getOne(id);
		model.addAttribute(vendCoupon);
		return "manage/coupon/coupon_edit";
	}
	/**
	 * 修改优惠券信息
	 * @param vendCoupon
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"coupon:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(@Validated VendCoupon vendCoupon,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/coupon/coupon_edit";
    	}
    	int isOk=vendCouponService.editVendCoupon(vendCoupon);
		return "redirect:coupons";
	}
    /**
     * 删除优惠券信息
     * @param user
     * @param br
     * @return
     */
	@RequiresPermissions({"coupon:del"})
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendCouponService.delVendCoupon(id);;
 		return "redirect:/coupon/coupons";
 	}
    /**
     * 批量删除优惠券信息
     * @param ids
     * @return
     */
	@RequiresPermissions({"coupon:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
    	int isOk=vendCouponService.delVendCoupons(idArray1);
  		return "redirect:/coupon/coupons";
  	}
}
