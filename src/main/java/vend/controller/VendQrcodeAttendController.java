package vend.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import vend.entity.VendQrcodeAttend;
import vend.entity.VendShopQrcode;
import vend.entity.VendUser;
import vend.service.VendQrcodeAttendService;
import vend.service.VendShopQrcodeService;
import vend.service.VendUserService;

@Controller
@RequestMapping("/qrcodeAtt")
public class VendQrcodeAttendController{
	public static Logger logger = Logger.getLogger(VendQrcodeAttendController.class);
	
	@Autowired
	VendQrcodeAttendService vendQrcodeAttendService;
	@Autowired
	VendShopQrcodeService vendShopQrcodeService;
	@Autowired
	VendUserService vendUserService;
	/**
	 * 根据输入信息条件查询二维码关注列表，并分页显示
	 * @param model
	 * @param vendQrcodeAttend
	 * @param page
	 * @param request
	 * @returnqrcodeattend:qrcodeattends
	 */
	@RequiresPermissions({"qrcodeattend:qrcodeattends"})
	@RequestMapping(value="/qrcodeAtts")
	public String listVendQrcodeAttend(Model model,@ModelAttribute VendQrcodeAttend vendQrcodeAttend, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendQrcodeAttend.toString());
		HttpSession session=request.getSession();
    	VendUser user=(VendUser)session.getAttribute("vendUser");
    	String userlist="";
		if(user!=null&&user.getUsercode()!=null){//上级账号
			userlist=vendUserService.getNextUsersOwnSelf(user.getUsercode());
		}
		List<VendShopQrcode> vendShopQrcodes=vendShopQrcodeService.findAll();
		model.addAttribute("vendShopQrcodes",vendShopQrcodes);
		List<VendQrcodeAttend> vendQrcodeAttends = vendQrcodeAttendService.listVendQrcodeAttend(vendQrcodeAttend, page);
		List<VendQrcodeAttend> list=new ArrayList<VendQrcodeAttend>();
		for(VendQrcodeAttend vendQrcodeAttend1:vendQrcodeAttends){
			if(StringUtils.indexOf(userlist, vendQrcodeAttend1.getExtend1())!=-1){
				vendQrcodeAttend1.setUsercode(vendUserService.getOne(vendQrcodeAttend1.getUsercode()).getUsername());
				vendQrcodeAttend1.setExtend1(vendUserService.getOne(vendQrcodeAttend1.getExtend1()).getUsername());
			    list.add(vendQrcodeAttend1); 
			}
		}
		model.addAttribute("vendQrcodeAttends",list);
		return "manage/qrcodeAtt/qrcodeAtt_list";
	}
	/**
	 * 跳转二维码关注信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"qrcodeattend:add"})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String qrcodeAttd(Model model){
		model.addAttribute(new VendQrcodeAttend());
		return "manage/qrcodeAtt/qrcodeAtt_add";
	}
   /**
    * 添加二维码关注信息
    * @param request
    * @param model
    * @param vendQrcodeAttend
    * @param br
    * @return
    */
	@RequiresPermissions({"qrcodeattend:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String qrcodeAttd(HttpServletRequest request,Model model,@Validated VendQrcodeAttend vendQrcodeAttend,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/qrcodeAtt/qrcodeAtt_add";
    	}
    	vendQrcodeAttendService.insertVendQrcodeAttend(vendQrcodeAttend);
    	return "redirect:qrcodeAtts";
	}
    /**
	 * 跳转二维码关注修改界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"qrcodeattend:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendQrcodeAttend vendQrcodeAttend=vendQrcodeAttendService.getOne(id);
		model.addAttribute(vendQrcodeAttend);
		return "manage/qrcodeAtt/qrcodeAtt_edit";
	}
	/**
	 * 修改二维码关注信息
	 * @param request
	 * @param model
	 * @param vendQrcodeAttend
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"qrcodeattend:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,@Validated VendQrcodeAttend vendQrcodeAttend,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/qrcodeAtt/qrcodeAtt_edit";
    	}
    	vendQrcodeAttendService.editVendQrcodeAttend(vendQrcodeAttend);
		return "redirect:qrcodeAtts";
	}
    /**
     * 删除二维码关注信息
     * @param user
     * @param br
     * @return
     */
	@RequiresPermissions({"qrcodeattend:del"})
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendQrcodeAttendService.delVendQrcodeAttend(id);;
 		return "redirect:/qrcodeAtt/qrcodeAtts";
 	}
    /**
     * 批量删除二维码关注信息
     * @param ids
     * @return
     */
	@RequiresPermissions({"qrcodeattend:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
    	vendQrcodeAttendService.delVendQrcodeAttends(idArray1);
  		return "redirect:/qrcodeAtt/qrcodeAtts";
  	}
	/**
	 * 用户关注商家二维码后发送的请求
	 * @param map
	 * @throws IOException
	 */
	@RequestMapping(value="/jusecoupons",method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody void getuseJson(Map<String,String> map) throws IOException {
		Date attendTime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
		String usercode=map.get("usercode");
		int qrcodeId=Function.getInt(map.get("qrcodeId"),0);
		
		VendQrcodeAttend vendQrcodeAttend =new VendQrcodeAttend();
		vendQrcodeAttend.setUsercode(usercode);
		vendQrcodeAttend.setQrcodeId(qrcodeId);
		vendQrcodeAttend.setAttendTime(attendTime);
		vendQrcodeAttendService.insertVendQrcodeAttend(vendQrcodeAttend);
		
		VendShopQrcode vendShopQrcode=vendShopQrcodeService.getOne(qrcodeId);
		if(vendShopQrcode!=null){
			vendShopQrcode.setAttenNum(vendShopQrcode.getAttenNum()+1);
			vendShopQrcodeService.editVendShopQrcode(vendShopQrcode);
		}
		
	}
}
