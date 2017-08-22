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
import vend.entity.VendRole;
import vend.service.VendRoleService;

@Controller
@RequestMapping("/role")
public class VendRoleController{
	public static Logger logger = Logger.getLogger(VendRoleController.class);
	
	@Autowired
	VendRoleService vendRoleService;
	/**
	 * 根据输入信息条件查询角色列表，并分页显示
	 * @param model
	 * @param vendRole
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/roles")
	public String listVendRole(Model model,@ModelAttribute VendRole vendRole, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendRole.toString());
		List<VendRole> vendRoles = vendRoleService.listVendRole(vendRole, page);
		model.addAttribute("vendRoles",vendRoles);
		return "manage/role/role_list";
	}
	/**
	 * 跳转角色信息添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new VendRole());
		return "manage/role/role_add";
	}
   /**
    * 添加角色信息
    * @param model
    * @param vendRole
    * @param br
    * @return
    */
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,@Validated VendRole vendRole,BindingResult br){
    	VendRole vendRole1= vendRoleService.selectByRoleName(vendRole.getRoleName());
    	if(vendRole1==null){
    		br.rejectValue("roleName", "NotRepeat", "角色名重复");
    	}
    	if(br.hasErrors()){
    		return "manage/role/role_add";
    	}
    	vendRoleService.insertVendRole(vendRole);
    	return "redirect:roles";
	}
    /**
	 * 跳转角色修改界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendRole vendRole=vendRoleService.getOne(id);
		model.addAttribute(vendRole);
		return "manage/role/role_edit";
	}
	/**
	 * 修改角色信息
	 * @param model
	 * @param vendRole
	 * @param br
	 * @return
	 */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Model model,@Validated VendRole vendRole,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/role/role_edit";
    	}
    	int isOk=vendRoleService.editVendRole(vendRole);
		return "redirect:roles";
	}
    /**
     * 删除角色信息
     * @param user
     * @param br
     * @return
     */
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendRoleService.delVendRole(id);;
 		return "redirect:/role/roles";
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
    	int isOk=vendRoleService.delVendRoles(idArray1);
  		return "redirect:/role/roles";
  	}
}
