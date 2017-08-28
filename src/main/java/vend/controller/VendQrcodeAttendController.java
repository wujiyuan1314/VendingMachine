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
import vend.entity.VendQrcodeAttend;
import vend.entity.VendShopQrcode;
import vend.service.VendQrcodeAttendService;
import vend.service.VendShopQrcodeService;

@Controller
@RequestMapping("/qrcodeAtt")
public class VendQrcodeAttendController{
	public static Logger logger = Logger.getLogger(VendQrcodeAttendController.class);
	
	@Autowired
	VendQrcodeAttendService vendQrcodeAttendService;
	@Autowired
	VendShopQrcodeService vendShopQrcodeService;
	/**
	 * 根据输入信息条件查询二维码关注列表，并分页显示
	 * @param model
	 * @param vendQrcodeAttend
	 * @param page
	 * @param request
	 * @return
	 */
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
		List<VendShopQrcode> vendShopQrcodes=vendShopQrcodeService.findAll();
		model.addAttribute("vendShopQrcodes",vendShopQrcodes);
		List<VendQrcodeAttend> vendQrcodeAttends = vendQrcodeAttendService.listVendQrcodeAttend(vendQrcodeAttend, page);
		model.addAttribute("vendQrcodeAttends",vendQrcodeAttends);
		return "manage/qrcodeAtt/qrcodeAtt_list";
	}
	/**
	 * 跳转二维码关注信息添加界面
	 * @param model
	 * @return
	 */
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
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,@Validated VendQrcodeAttend vendQrcodeAttend,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/qrcodeAtt/qrcodeAtt_edit";
    	}
    	int isOk=vendQrcodeAttendService.editVendQrcodeAttend(vendQrcodeAttend);
		return "redirect:qrcodeAtts";
	}
    /**
     * 删除二维码关注信息
     * @param user
     * @param br
     * @return
     */
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
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
    	int isOk=vendQrcodeAttendService.delVendQrcodeAttends(idArray1);
  		return "redirect:/qrcodeAtt/qrcodeAtts";
  	}
}
