package vend.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.util.DateUtil;
import base.util.Function;
import net.sf.json.JSONArray;
import vend.entity.Menuitem;
import vend.entity.VendPermission;
import vend.entity.VendRole;
import vend.entity.VendRolePermission;
import vend.entity.ZNode;
import vend.service.MenuitemService;
import vend.service.VendRoleService;

@Controller
@RequestMapping("/menuitem")
public class MenuitemController {
	public static Logger logger = Logger.getLogger(MenuitemController.class);
	
	@Autowired
	MenuitemService menuitemService;
	@Autowired
	VendRoleService vendRoleService;
	/**
	 * 跳转到列表页
	 * @return
	 */
	@RequiresPermissions({"menuitem:dels"})
	@RequestMapping(value="/menuitems")
	public String listMenuitem() {
		return "manage/menuitem/menuitem_list";
	}
	/**
	 * 得到权限Json数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getJson",method=RequestMethod.POST)
	public void getJson(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<Menuitem> menuitems = menuitemService.findAll();
		List<ZNode> list=new ArrayList();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		for(Menuitem menuitem:menuitems){
			ZNode zNode=new ZNode();
			zNode.setId(menuitem.getId());
			zNode.setpId(menuitem.getParentId());
			zNode.setName(menuitem.getMenuName());
			zNode.setFile(basePath+"/menuitem/"+menuitem.getId()+"/edit");
		    zNode.setOpen(true);
		    list.add(zNode);
		}
		JSONArray json = JSONArray.fromObject(list);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.print(json);
	}
	/**
	 * 得到权限Json数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getJson1",method=RequestMethod.POST)
	public void getJson1(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String roleId=request.getParameter("roleId");
		int roleId1=Function.getInt(roleId, 0);
		VendRole vendRole=vendRoleService.getOne(roleId1);
		String menulist=vendRole.getExtend1();
		if(menulist==null){
			menulist="";
		}
		
		List<Menuitem> menuitems = menuitemService.findAll();
		List<ZNode> list=new ArrayList();
		for(Menuitem menuitem:menuitems){
			ZNode zNode=new ZNode();
			zNode.setId(menuitem.getId());
			zNode.setpId(menuitem.getParentId());
			zNode.setName(menuitem.getMenuName());
			zNode.setOpen(true);
			if(menulist.indexOf(menuitem.getId().toString())!=-1){
				zNode.setChecked(true);
			}
		    list.add(zNode);
		}
		JSONArray json = JSONArray.fromObject(list);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.print(json);
	}
	/**
	 * 跳转菜单信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"menuitem:add"})
	@RequestMapping(value="/{pid}/add",method=RequestMethod.GET)
	public String addmenuitem(Model model,@PathVariable int pid){
    	model.addAttribute("pid", pid);
		model.addAttribute(new Menuitem());
		return "manage/menuitem/menuitem_add";
	}
   /**
    * 添加菜单信息
    * @param menuitem
    * @param br
    * @return
    */
	@RequiresPermissions({"menuitem:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String addmenuitem(@Validated Menuitem menuitem,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/menuitem/menuitem_add";
    	}
    	Date createtime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
    	menuitem.setCreateTime(createtime);
    	menuitem.setUpdataTime(createtime);
    	menuitemService.insertMenuitem(menuitem);
    	return "manage/menuitems/menuitems_add";
	}
    /**
     * 跳转菜单修改界面
     * @param model
     * @param id
     * @return
     */
	@RequiresPermissions({"menuitem:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String editMenuitem(Model model,@PathVariable int id){
		Menuitem menuitem=menuitemService.getMenuitemByID(id);
		model.addAttribute(menuitem);
		return "manage/menuitem/menuitem_edit";
	}
   /**
    * 修改菜单信息
    * @param menuitem
    * @param br
    * @return
    */
	@RequiresPermissions({"menuitem:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editMenuitem(@Validated Menuitem menuitem,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/menuitem/menuitem_edit";
    	}
    	int isOk=menuitemService.editMenuitem(menuitem);
		return "manage/menuitem/menuitem_edit";
	}
    /**
     * 删除菜单信息
     * @param user
     * @param br
     * @return
     */
	@RequiresPermissions({"menuitem:del"})
     @RequestMapping(value="/{id}/del")
 	public String delmenuitem(@PathVariable int id){
    	 menuitemService.deleteMenuitem(id);
 		return "redirect:/menuitem/menuitems";
 	}
     /**
      * 批量删除菜单信息
      * @param ids
      * @return
      */
	@RequiresPermissions({"menuitem:dels"})
      @RequestMapping(value="/dels")
  	public String menuitem(int ids[]){
    	  menuitemService.deleteMenuitems(ids);
  		return "manage/menuitem/menuitem_list";
  	}
}
