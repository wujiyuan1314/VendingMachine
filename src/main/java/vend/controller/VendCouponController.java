package vend.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.alibaba.fastjson.JSONObject;

import base.util.DateUtil;
import base.util.Function;
import base.util.Page;
import vend.entity.CodeLibrary;
import vend.entity.UserCoupon;
import vend.entity.VendCoupon;
import vend.entity.VendUser;
import vend.service.CodeLibraryService;
import vend.service.UserCouponService;
import vend.service.VendCouponService;
import vend.service.VendParaService;
import vend.service.VendUserService;

@Controller
@RequestMapping("/coupon")
public class VendCouponController{
	public static Logger logger = Logger.getLogger(VendCouponController.class);
	
	@Autowired
	VendCouponService vendCouponService;
	@Autowired
	VendUserService vendUserService;
	@Autowired
	UserCouponService userCouponService;
	@Autowired
	CodeLibraryService codeLibraryService;
	@Autowired
	VendParaService vendParaService;
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
	 * 充值优惠活动列表
	 * @param model
	 * @param VendCoupon
	 * @param page
	 * @param request
	 * @return
	 */
	@RequiresPermissions({"coupon:chargecoupons"})
	@RequestMapping(value="/chargecoupons")
	public String listReChargeCoupon(Model model,@ModelAttribute VendCoupon vendCoupon, @ModelAttribute Page page,HttpServletRequest request) {
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
		vendCoupon.setExtend1("3");
		List<VendCoupon> vendCoupons = vendCouponService.listVendCoupon(vendCoupon, page);
		model.addAttribute("vendCoupons",vendCoupons);
		return "manage/coupon/recharge_list";
	}
	/**
	 * 得到消费用户的已领取的优惠券信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/jusecoupons",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<UserCoupon> getuseJson(HttpServletRequest request) throws IOException {
		String currentDate=DateUtil.getCurrentDateStr();
		String usercode=request.getParameter("usercode");
		List<UserCoupon> userCoupons = userCouponService.findByUsercode(usercode,currentDate);
		return userCoupons;
	}
	/**
	 * 用户领取优惠券
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/lqcoupon",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String lQcoupon(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		json.put("success", "0");
		json.put("msg", "领取失败");
		
		String currentDate=DateUtil.getCurrentDateStr();
		String usercode=request.getParameter("usercode");
		int id=Function.getInt(request.getParameter("id"), 0);
		List<UserCoupon> userCoupons = userCouponService.findByUsercodeNull(usercode,currentDate);
		if(userCoupons.size()==0){
			json.put("success", "0");
			json.put("msg", "您没有领取资格");
		}else{
			for(UserCoupon userCoupon:userCoupons){
				if(userCoupon.getId()==id){
					userCoupon.setExtend1("1");
					userCouponService.editUserCoupon(userCoupon);
				}
			}
			json.put("success", "1");
			json.put("msg", "领取成功");
		}
		
		try {
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 得到消费用户未领取的优惠券数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/jcoupons",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody List<UserCoupon> getJson(HttpServletRequest request) throws IOException {
		String currentDate=DateUtil.getCurrentDateStr();
		String usercode=request.getParameter("usercode");
		List<UserCoupon> userCoupons = userCouponService.findByUsercodeNull(usercode,currentDate);
		return userCoupons;
	}
	/**
	 * 跳转优惠券信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"coupon:add"})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
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
	public String add(HttpServletRequest request,Model model,@Validated VendCoupon vendCoupon,BindingResult br){
		double couponPrice=Function.getDouble(vendParaService.selectByParaCode("coupon_price"),0.00);
		if(vendCoupon.getCouponScale().doubleValue()>couponPrice){
			br.rejectValue("couponScale", "NOTBELOGNGOODSPRICE", "优惠券金额不能高于商品最高金额");
		}
		String newareaId[]=request.getParameterValues("areaId");
		if(newareaId==null||newareaId.length==0){
			br.rejectValue("areaId", "NOCHOOSEAREA", "请选择地区");
		}
		
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		model.addAttribute("couponareas", couponareas);
    	if(br.hasErrors()){
    		return "manage/coupon/coupon_add";
    	}
    	String newareaIds="";
    	for(String str:newareaId){
    		newareaIds+=str+",";
    	}
    	vendCoupon.setAreaId(newareaIds);
    	vendCoupon.setValid("0");
    	vendCoupon.setExtend1("1");
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
		double couponPrice=Function.getDouble(vendParaService.selectByParaCode("coupon_price"),0.00);
		if(vendCoupon.getCouponScale().doubleValue()>couponPrice){
			br.rejectValue("couponScale", "NOTBELOGNGOODSPRICE", "优惠券金额不能高于商品最高金额");
		}
		String newareaId[]=request.getParameterValues("areaId");
		if(newareaId==null||newareaId.length==0){
			br.rejectValue("areaId", "NOCHOOSEAREA", "请选择地区");
		}
		
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		String areaId="";
		if(vendCoupon!=null){
			areaId=vendCoupon.getAreaId();
			if(areaId==null){
				areaId="";
			}
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
        vendCoupon.setValid("0");
    	vendCouponService.editVendCoupon(vendCoupon);
		return "redirect:coupons";
	}
	
	/**
	 * 跳转充值活动添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"coupon:rechargeadd"})
	@RequestMapping(value="/rechargeadd",method=RequestMethod.GET)
	public String rechargeadd(Model model){
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		model.addAttribute("couponareas", couponareas);
		model.addAttribute(new VendCoupon());
		return "manage/coupon/recharge_add";
	}
   /**
    * 添加充值活动
    * @param request
    * @param model
    * @param vendCoupon
    * @param br
    * @return
    */
	@RequiresPermissions({"coupon:rechargeadd"})
    @RequestMapping(value="/rechargeadd",method=RequestMethod.POST)
	public String rechargeadd(HttpServletRequest request,Model model,@Validated VendCoupon vendCoupon,BindingResult br){
		double couponPrice=Function.getDouble(vendParaService.selectByParaCode("coupon_price"),0.00);
		if(vendCoupon.getCouponScale().doubleValue()>couponPrice){
			br.rejectValue("couponScale", "NOTBELOGNGOODSPRICE", "优惠券金额不能高于商品最高金额");
		}
		String newareaId[]=request.getParameterValues("areaId");
		if(newareaId==null||newareaId.length==0){
			br.rejectValue("areaId", "NOCHOOSEAREA", "请选择地区");
		}
		
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		model.addAttribute("couponareas", couponareas);
    	if(br.hasErrors()){
    		return "manage/coupon/recharge_add";
    	}
    	String newareaIds="";
    	for(String str:newareaId){
    		newareaIds+=str+",";
    	}
    	vendCoupon.setAreaId(newareaIds);
    	vendCoupon.setValid("0");
    	vendCoupon.setExtend1("3");
    	vendCouponService.insertVendCoupon(vendCoupon);
    	return "redirect:chargecoupons";
	}
    /**
     * 跳转优惠券修改界面
     * @param model
     * @param id
     * @return
     */
	@RequiresPermissions({"coupon:rechargeedit"})
	@RequestMapping(value="/{id}/rechargeedit",method=RequestMethod.GET)
	public String rechargeedit(Model model,@PathVariable int id){
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
		return "manage/coupon/recharge_edit";
	}
	/**
	 * 修改充值活动
	 * @param request
	 * @param model
	 * @param vendCoupon
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"coupon:rechargeedit"})
    @RequestMapping(value="/rechargeedit",method=RequestMethod.POST)
	public String rechargeedit(HttpServletRequest request,Model model,@Validated VendCoupon vendCoupon,BindingResult br){
		double couponPrice=Function.getDouble(vendParaService.selectByParaCode("coupon_price"),0.00);
		if(vendCoupon.getCouponScale().doubleValue()>couponPrice){
			br.rejectValue("couponScale", "NOTBELOGNGOODSPRICE", "优惠券金额不能高于商品最高金额");
		}
		String newareaId[]=request.getParameterValues("areaId");
		if(newareaId==null||newareaId.length==0){
			br.rejectValue("areaId", "NOCHOOSEAREA", "请选择地区");
		}
		
		List<CodeLibrary> couponareas=codeLibraryService.selectByCodeNo("COUPONAREA");
		String areaId="";
		if(vendCoupon!=null){
			areaId=vendCoupon.getAreaId();
			if(areaId==null){
				areaId="";
			}
		}
		for(CodeLibrary couponarea:couponareas){
			if(areaId.indexOf(couponarea.getItemname()+",")!=-1){
				couponarea.setExtend2("1");
			}
		}
		model.addAttribute("couponareas", couponareas);
    	if(br.hasErrors()){
    		return "manage/coupon/recharge_edit";
    	}
    	String newareaIds="";
    	for(String str:newareaId){
    		newareaIds+=str+",";
    	}
    	vendCoupon.setAreaId(newareaIds);
        vendCoupon.setValid("0");
        vendCoupon.setExtend1("3");
    	vendCouponService.editVendCoupon(vendCoupon);
		return "redirect:chargecoupons";
	}
	
	/**
	 * 投放优惠券
	 * @param id
	 * @return
	 */
    @RequestMapping(value="/{id}/puton")
 	public String putOn(@PathVariable Integer id){
    	VendCoupon vendCoupon=vendCouponService.getOne(id);
    	vendCoupon.setValid("1");
    	int isOk=vendCouponService.editVendCoupon(vendCoupon);
    	
    	if(!vendCoupon.getExtend1().equals("3")){
    		//投放后该优惠券适用地区自动获得该优惠券
        	if(isOk==1){
        		Date createTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
        		String arealist=vendCoupon.getAreaId();
        		if(arealist!=null){
        			String arealistArray[]=Function.stringSpilit(arealist, ",");
        			List<VendUser> vendUsers=vendUserService.selectByArealist(arealistArray);
        			for(VendUser vendUser:vendUsers){
        				if(vendUser!=null){
        					if(vendUser.getUsercode()!=null){
        						UserCoupon userCoupon=userCouponService.findByUsercodeLimitCouponId(vendUser.getUsercode(), id);
        						if(userCoupon!=null){
                					userCoupon.setCouponId(id);
                					userCoupon.setCreateTime(createTime);
                					userCoupon.setExtend2(DateUtil.formatTime(vendCoupon.getStartTime()));//开始时间
                					userCoupon.setExtend3(DateUtil.formatTime(vendCoupon.getEndTime()));//结束时间
                					userCoupon.setExtend4(vendCoupon.getCouponScale().toString());//优惠金额
                					userCoupon.setExtend5(vendCoupon.getCouponName());//优惠券名
                					userCoupon.setExtend6(vendCoupon.getCouponInfo());//优惠券信息
                					userCouponService.editUserCoupon(userCoupon);
        						}else{
        							userCoupon=new UserCoupon();
        							userCoupon.setUsercode(vendUser.getUsercode());
        							userCoupon.setCouponId(id);
                					userCoupon.setCreateTime(createTime);
                					userCoupon.setExtend2(DateUtil.formatTime(vendCoupon.getStartTime()));//开始时间
                					userCoupon.setExtend3(DateUtil.formatTime(vendCoupon.getEndTime()));//结束时间
                					userCoupon.setExtend4(vendCoupon.getCouponScale().toString());//优惠金额
                					userCoupon.setExtend5(vendCoupon.getCouponName());//优惠券名
                					userCoupon.setExtend6(vendCoupon.getCouponInfo());//优惠券信息
                					userCouponService.insertUserCoupon(userCoupon);
        						}
        					}
        				}
        			}
        		}
        	}	
    	}
    	String returnStr="";
    	if(!vendCoupon.getExtend1().equals("3")){
    		returnStr="redirect:/coupon/coupons";
    	}else{
    		returnStr="redirect:/coupon/chargecoupons";
    	}
 		return returnStr;
 	}
    /**
	 * 收回优惠券
	 * @param id
	 * @return
	 */
    @RequestMapping(value="/{id}/revoke")
 	public String revoke(@PathVariable Integer id){
    	VendCoupon vendCoupon=vendCouponService.getOne(id);
    	vendCoupon.setValid("0");
    	vendCouponService.editVendCoupon(vendCoupon);
    	String returnStr="";
    	if(!vendCoupon.getExtend1().equals("3")){
    		returnStr="redirect:/coupon/coupons";
    	}else{
    		returnStr="redirect:/coupon/chargecoupons";
    	}
 		return returnStr;
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
