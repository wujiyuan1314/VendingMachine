package vend.controller;
import java.util.ArrayList;
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

import base.util.Function;
import base.util.Page;
import vend.entity.CodeLibrary;
import vend.entity.VendAd;
import vend.entity.VendMachine;
import vend.entity.VendShopQrcode;
import vend.entity.VendUser;
import vend.service.CodeLibraryService;
import vend.service.VendAdService;
import vend.service.VendMachineService;
import vend.service.VendShopQrcodeService;
import vend.service.VendUserService;

@Controller
@RequestMapping("/machine")
public class VendMachineController{
	public static Logger logger = Logger.getLogger(VendMachineController.class);
	
	@Autowired
	VendMachineService vendMachineService;
	@Autowired
	VendAdService vendAdService;
	@Autowired
	VendUserService vendUserService;
	@Autowired
	VendShopQrcodeService vendShopQrcodeService;
	@Autowired
	CodeLibraryService codeLibraryService;
	/**
	 * 根据输入信息条件查询机器列表，并分页显示
	 * @param model
	 * @param vendMachine
	 * @param page
	 * @param request
	 * @return
	 */
	@RequiresPermissions({"machine:machines"})
	@RequestMapping(value="/machines")
	public String listVendMachine(Model model,@ModelAttribute VendMachine vendMachine, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendMachine.toString());
		HttpSession session=request.getSession();
		VendUser user=(VendUser)session.getAttribute("vendUser");
		List<CodeLibrary> usestatus=codeLibraryService.selectByCodeNo("USESTATUS");
		model.addAttribute("usestatus", usestatus);
		List<VendMachine> vendMachines = vendMachineService.listVendMachine(vendMachine, page);
		String userlist="";
		if(user!=null&&user.getUsercode()!=null){//上级账号
			userlist=vendUserService.getNextUsersOwnSelf(user.getUsercode());
		}
		List<VendMachine> vendMachines1=new ArrayList<VendMachine>();
		if(userlist!=null){
			for(VendMachine vendMachine1:vendMachines){
				if(userlist.indexOf(vendMachine1.getUsercode())!=-1){
					vendMachines1.add(vendMachine1);
				}
			}
		}
		model.addAttribute("vendMachines",vendMachines1);
		return "manage/machine/machine_list";
	}
	/**
	 * 开机
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/open")
	public String openMachine(@PathVariable int id){
		VendMachine vendMachine=vendMachineService.getOne(id);
		if(vendMachine!=null){
			vendMachine.setUseStatus("1");
			vendMachineService.editVendMachine(vendMachine);
		}
		return "redirect:../machines"; 
	}
	/**
	 * 关机
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/shutdown")
	public String shutDownMachine(@PathVariable int id){
		VendMachine vendMachine=vendMachineService.getOne(id);
		if(vendMachine!=null){
			vendMachine.setUseStatus("2");
			vendMachineService.editVendMachine(vendMachine);
		}
		return "redirect:../machines"; 
	}
	/**
	 * 重启
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/reboot")
	public String reBootMachine(@PathVariable int id){
		//VendMachine vendMachine=vendMachineService.getOne(id);
		return "redirect:../machines"; 
	}
	/**
	 * 详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}/detail")
	public String detail(Model model,@PathVariable int id){
		List<VendShopQrcode> clientQrcodes=vendShopQrcodeService.selectByType("2");
		model.addAttribute("clientQrcodes", clientQrcodes);
		VendMachine vendMachine=vendMachineService.getOne(id);
		model.addAttribute("vendMachine", vendMachine);
		return "manage/machine/machine_detail"; 
	}
	/**
	 * 跳转机器信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"machine:add"})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String machined(Model model){
		model.addAttribute(new VendMachine());
		return "manage/machine/machine_add";
	}
   /**
    * 添加机器信息
    * @param request
    * @param model
    * @param vendMachine
    * @param br
    * @return
    */
	@RequiresPermissions({"machine:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String machined(HttpServletRequest request,Model model,@Validated VendMachine vendMachine,BindingResult br){
		VendMachine vendMachine1=vendMachineService.selectByMachineId(vendMachine.getMachineId());
		if(vendMachine1!=null){
			br.rejectValue("machineId", "MACHINEIDREPPEAT", "该机器ID已被绑定");
		}
		VendMachine vendMachine2=vendMachineService.selectByMachineCode(vendMachine.getMachineCode());
		if(vendMachine2!=null){
			br.rejectValue("machineId", "MACHINECODEREPPEAT", "该机器码已被绑定");
		}
    	if(br.hasErrors()){
    		return "manage/machine/machine_add";
    	}
    	vendMachineService.insertVendMachine(vendMachine);
    	return "redirect:machines";
	}
    /**
	 * 跳转机器修改界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"machine:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		List<VendAd> ads=vendAdService.findAll();
		model.addAttribute("ads", ads);
		List<VendShopQrcode> vendShopQrcodes=vendShopQrcodeService.selectByType("1");
		model.addAttribute("vendShopQrcodes", vendShopQrcodes);
		VendMachine vendMachine=vendMachineService.getOne(id);
		model.addAttribute(vendMachine);
		return "manage/machine/machine_edit";
	}
	/**
	 * 修改机器信息
	 * @param request
	 * @param model
	 * @param vendMachine
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"machine:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,@Validated VendMachine vendMachine,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/machine/machine_edit";
    	}
    	vendMachineService.editVendMachine(vendMachine);
		return "redirect:machines";
	}
    /**
     * 删除机器信息
     * @param user
     * @param br
     * @return
     */
	@RequiresPermissions({"machine:del"})
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendMachineService.delVendMachine(id);;
 		return "redirect:/machine/machines";
 	}
    /**
     * 批量删除机器信息
     * @param ids
     * @return
     */
	@RequiresPermissions({"machine:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
    	vendMachineService.delVendMachines(idArray1);
  		return "redirect:/machine/machines";
  	}
}
