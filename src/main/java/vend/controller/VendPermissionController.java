package vend.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import net.sf.json.JSONArray;
import vend.entity.VendGoods;
import vend.entity.VendPermission;
import vend.entity.VendRolePermission;
import vend.entity.VendUser;
import vend.entity.ZNode;
import vend.service.VendPermissionService;
import vend.service.VendRolePermissionService;

@Controller
@RequestMapping("/permission")
public class VendPermissionController{
	public static Logger logger = Logger.getLogger(VendPermissionController.class);
	
	@Autowired
	VendPermissionService vendPermissionService;
	@Autowired
	VendRolePermissionService vendRolePermissionService;
	/**
	 * 跳转到列表页
	 * @return
	 */
	@RequiresPermissions({"permission:permissions"})
	@RequestMapping(value="/permissions")
	public String listVendPermission() {
		return "manage/permission/permission_list";
	}
	/**
	 * 得到权限Json数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getJson",method=RequestMethod.POST)
	public void getJson(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<VendPermission> vendPermissions = vendPermissionService.findAll();
		List<ZNode> list=new ArrayList<ZNode>();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		for(VendPermission vendPermission:vendPermissions){
			ZNode zNode=new ZNode();
			zNode.setId(vendPermission.getId());
			zNode.setpId(vendPermission.getParentId());
			zNode.setName(vendPermission.getPermissionDescription());
			zNode.setFile(basePath+"/permission/"+vendPermission.getId()+"/edit");
			if(vendPermission.getId()==1){
				zNode.setOpen(true);
			}else{
				zNode.setOpen(false);
			}
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
		HttpSession session=request.getSession();
		VendUser user=(VendUser)session.getAttribute("vendUser");
		int currentRoleId=user.getRoleId();//当前登录用户的roleId
		
		String roleId=request.getParameter("roleId");
		int roleId1=Function.getInt(roleId, 0);
		List<VendRolePermission> vendRolePermissions=vendRolePermissionService.selectByRoleId(roleId1);
		int roleIdArray[]=new int[vendRolePermissions.size()];
		for(int i=0;i<vendRolePermissions.size();i++){
			roleIdArray[i]=vendRolePermissions.get(i).getPermissionId();
		}
		
		List<ZNode> list=new ArrayList<ZNode>();
		String parentId=request.getParameter("parentId");
		int parentId1=Function.getInt(parentId, 0);
		if(parentId1==0){
			List<VendPermission> vendPermissions = vendPermissionService.findAll();
			for(VendPermission vendPermission:vendPermissions){
				ZNode zNode=new ZNode();
				zNode.setId(vendPermission.getId());
				zNode.setpId(vendPermission.getParentId());
				zNode.setName(vendPermission.getPermissionDescription());
				if(vendPermission.getId()==1){
					zNode.setOpen(true);
				}else{
					zNode.setOpen(false);
				}
				for(int roleId2:roleIdArray){
					if(roleId2==vendPermission.getId()){
						zNode.setChecked(true);
						zNode.setOpen(true);
						break;
					}
				}
			    list.add(zNode);
			}
		}else{
			List<VendRolePermission> vendPermissions = vendRolePermissionService.selectByRoleId(parentId1);	
			for(VendRolePermission vendRolePermission:vendPermissions){
				if(currentRoleId!=roleId1){
					VendPermission vendPermission=vendPermissionService.getOne(vendRolePermission.getPermissionId());
					ZNode zNode=new ZNode();
					zNode.setId(vendPermission.getId());
					zNode.setpId(vendPermission.getParentId());
					zNode.setName(vendPermission.getPermissionDescription());
					if(vendPermission.getId()==1){
						zNode.setOpen(true);
					}else{
						zNode.setOpen(false);
					}
					zNode.setChecked(false);
					for(int roleId2:roleIdArray){
						if(vendPermission.getId()==roleId2){
							zNode.setChecked(true);
						}
					}
					list.add(zNode);
				}else{
					for(int roleId2:roleIdArray){
						if(roleId2==vendRolePermission.getPermissionId()){
							VendPermission vendPermission=vendPermissionService.getOne(vendRolePermission.getPermissionId());
							ZNode zNode=new ZNode();
							zNode.setId(vendPermission.getId());
							zNode.setpId(vendPermission.getParentId());
							zNode.setName(vendPermission.getPermissionDescription());
							if(vendPermission.getId()==1){
								zNode.setOpen(true);
							}else{
								zNode.setOpen(false);
							}
							zNode.setChecked(true);
							list.add(zNode);
						}
					}
				}
			}
		}
		JSONArray json = JSONArray.fromObject(list);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.print(json);
	}
	/**
	 * 跳转权限信息添加界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"permission:add"})
	@RequestMapping(value="/{pid}/add",method=RequestMethod.GET)
	public String add(Model model,@PathVariable int pid){
		VendPermission vendPermission=new VendPermission();
		vendPermission.setParentId(pid);
		model.addAttribute(vendPermission);
		return "manage/permission/permission_add";
	}
   /**
    * 添加权限信息
    * @param model
    * @param vendPermission
    * @param br
    * @return
    */
	@RequiresPermissions({"permission:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,@Validated VendPermission vendPermission,BindingResult br){
    	VendPermission vendPermission1= vendPermissionService.selectByPermissionName(vendPermission.getPermissionName());
    	if(vendPermission1!=null){
    		br.rejectValue("permissionName", "NotRepeat", "权限名重复");
    	}
    	if(br.hasErrors()){
    		return "manage/permission/permission_add";
    	}
    	vendPermissionService.insertVendPermission(vendPermission);
    	return "manage/permission/permission_add";
	}
    /**
	 * 跳转权限修改界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"permission:edit"})
	@RequestMapping(value="/{id}/edit",method=RequestMethod.GET)
	public String edit(Model model,@PathVariable int id){
		VendPermission vendPermission=vendPermissionService.getOne(id);
		model.addAttribute(vendPermission);
		return "manage/permission/permission_edit";
	}
	/**
	 * 修改权限信息
	 * @param model
	 * @param vendPermission
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"permission:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(Model model,@Validated VendPermission vendPermission,BindingResult br){
    	if(br.hasErrors()){
    		return "manage/permission/permission_edit";
    	}
    	vendPermissionService.editVendPermission(vendPermission);
		return "manage/permission/permission_edit";
	}
    /**
     * 删除权限信息
     * @param user
     * @param br
     * @return
     */
	@RequiresPermissions({"permission:del"})
    @RequestMapping(value="/{id}/del")
 	public String del(@PathVariable Integer id){
    	vendPermissionService.delVendPermission(id);;
 		return "redirect:/permission/permissions";
 	}
    /**
     * 批量删除权限信息
     * @param ids
     * @return
     */
	@RequiresPermissions({"permission:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String ids=request.getParameter("ids");
    	String idArray[]=ids.split(",");
    	int[] idArray1=new int[idArray.length];
    	for(int i=0;i<idArray.length;i++){
    		idArray1[i]=Function.getInt(idArray[i], 0);
    	}
        vendPermissionService.delVendPermissions(idArray1);
  		return "redirect:/permission/permissions";
  	}
}
