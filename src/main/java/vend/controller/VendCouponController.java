package vend.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import base.util.DateUtil;
import base.util.Function;
import base.util.Page;
import vend.entity.CodeLibrary;
import vend.entity.UserCoupon;
import vend.entity.VendCoupon;
import vend.service.CodeLibraryService;
import vend.service.UserCouponService;
import vend.service.VendCouponService;

@Controller
@RequestMapping("/coupon")
public class VendCouponController{
	public static Logger logger = Logger.getLogger(VendCouponController.class);
	
	@Autowired
	VendCouponService vendCouponService;
	@Autowired
	UserCouponService userCouponService;
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
		List<CodeLibrary> coupons=codeLibraryService.selectByCodeNo("COUPONTYPE");
		model.addAttribute("coupons", coupons);
		vendCoupon.setExtend1("1");
		List<VendCoupon> vendCoupons = vendCouponService.listVendCoupon(vendCoupon, page);
		model.addAttribute("vendCoupons",vendCoupons);
		return "manage/coupon/coupon_list";
	}
	/**
	 * 得到消费用户的优惠券信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/jusecoupons",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<VendCoupon> getuseJson(HttpServletRequest request) throws IOException {
		String usercode=request.getParameter("usercode");
		List<UserCoupon> userCoupons = userCouponService.findByUsercode(usercode);
		List<VendCoupon> list=new ArrayList<VendCoupon>();
		for(UserCoupon userCoupon:userCoupons){
			VendCoupon vendCoupon=vendCouponService.getOne(userCoupon.getCouponId());
			if(vendCoupon!=null){
				list.add(vendCoupon);
			}
		}
		for(VendCoupon vendCoupon:list){
			vendCoupon.setExtend2(DateUtil.format(vendCoupon.getStartTime()));
			vendCoupon.setExtend3(DateUtil.format(vendCoupon.getEndTime()));
		}
		return list;
	}
	/**
	 * 得到优惠券数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/jcoupons",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<VendCoupon> getJson() throws IOException {
		List<VendCoupon> vendCoupons = vendCouponService.findAll();
		for(VendCoupon vendCoupon:vendCoupons){
			vendCoupon.setExtend2(DateUtil.format(vendCoupon.getStartTime()));
			vendCoupon.setExtend3(DateUtil.format(vendCoupon.getEndTime()));
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
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		model.addAttribute("couponareas", couponareas);
		model.addAttribute(new VendCoupon());
		return "manage/coupon/coupon_add";
	}
   /**
    * 添加优惠券信息
    * @param request
    * @param model
    * @param vendCoupon
    * @param br
    * @return
    */
	@RequiresPermissions({"coupon:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String coupond(HttpServletRequest request,Model model,@Validated VendCoupon vendCoupon,BindingResult br){
		String newareaId[]=request.getParameterValues("areaId");
		if(newareaId.length==0){
			br.rejectValue("roleId", "NOCHOOSEAREA", "请选择地区");
		}
		
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		model.addAttribute("couponareas", couponareas);
    	if(br.hasErrors()){
    		return "manage/coupon/coupon_coupond";
    	}
    	String newareaIds="";
    	for(String str:newareaId){
    		newareaIds+=str+",";
    	}
    	vendCoupon.setAreaId(newareaIds);
    	vendCouponService.insertVendCoupon(vendCoupon);
    	return "redirect:coupons";
	}
    /**
     * 跳转优惠券修改界面
     * @param model
     * @param id
     * @return
     */
	@RequiresPermissions({"coupon:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		VendCoupon vendCoupon=vendCouponService.getOne(id);
		String areaId="";
		if(vendCoupon!=null){
			areaId=vendCoupon.getAreaId();
		}
		if(areaId==null){
			areaId="";
		}
		for(CodeLibrary couponarea:couponareas){
			if(areaId.indexOf(couponarea.getItemname()+",")!=-1){
				couponarea.setExtend2("1");
			}else{
				couponarea.setExtend2("0");
			}
		}
		model.addAttribute("couponareas", couponareas);
		model.addAttribute(vendCoupon);
		return "manage/coupon/coupon_edit";
	}
	/**
	 * 修改优惠券信息
	 * @param request
	 * @param model
	 * @param vendCoupon
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"coupon:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,@Validated VendCoupon vendCoupon,BindingResult br){
		String newareaId[]=request.getParameterValues("areaId");
		if(newareaId.length==0){
			br.rejectValue("roleId", "NOCHOOSEAREA", "请选择地区");
		}
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		String areaId="";
		if(vendCoupon!=null){
			areaId=vendCoupon.getAreaId();
		}
		for(CodeLibrary couponarea:couponareas){
			if(areaId.indexOf(couponarea.getItemname()+",")!=-1){
				couponarea.setExtend2("1");
			}
		}
		model.addAttribute("couponareas", couponareas);
    	if(br.hasErrors()){
    		return "manage/coupon/coupon_edit";
    	}
    	String newareaIds="";
    	for(String str:newareaId){
    		newareaIds+=str+",";
    	}
    	vendCoupon.setAreaId(newareaIds);
    	vendCouponService.editVendCoupon(vendCoupon);
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
    	vendCouponService.delVendCoupons(idArray1);
  		return "redirect:/coupon/coupons";
  	}
}
