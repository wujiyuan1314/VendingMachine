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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import base.util.CacheUtils;
import base.util.Function;
import base.util.Page;
import net.sf.json.JSONArray;
import vend.entity.VendPermission;
import vend.entity.VendRole;
import vend.entity.VendRolePermission;
import vend.entity.VendUser;
import vend.entity.ZNode;
import vend.service.VendPermissionService;
import vend.service.VendRolePermissionService;
import vend.service.VendRoleService;
import vend.service.VendUserService;

@Controller
@RequestMapping("/user")
public class VendUserController{
	public static Logger logger = Logger.getLogger(VendUserController.class);
	
	@Autowired
	VendUserService vendUserService;
	@Autowired
	VendRoleService vendRoleService;
	@Autowired
	VendRolePermissionService vendRolePermissionService;
	@Autowired
	VendPermissionService vendPermissionService;
	/**
	 * 根据输入信息条件查询用户列表，并分页显示
	 * @param model
	 * @param vendUser
	 * @param page
	 * @param request
	 * @return
	 */
	@RequiresPermissions({"user:users"})
	@RequestMapping(value="/users")
	public String listVendUser(Model model,@ModelAttribute VendUser vendUser, @ModelAttribute Page page,HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(vendUser.toString());
		HttpSession session=request.getSession();
    	VendUser user=(VendUser)session.getAttribute("vendUser");
    	String userlist="";
		if(user!=null&&user.getUsercode()!=null){//上级账号
			userlist=vendUserService.getNextUsersOwnSelf(user.getUsercode());
		}
		String usersArray[]=Function.stringSpilit(userlist, ",");
		List<VendUser> vendUsers = vendUserService.listVendUser(vendUser,usersArray,page);
		model.addAttribute("vendUsers",vendUsers);
		return "manage/user/user_list";
	}
	/**
	 * 得到权限Json数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/getJson",method=RequestMethod.POST)
	public void getJson(HttpServletRequest request,HttpServletResponse response) throws IOException {	
		String usercode=request.getParameter("usercode");
		String roleId=request.getParameter("roleId");
		int roleId1=Function.getInt(roleId, 0);
		List<VendRolePermission> vendRolePermissions=vendRolePermissionService.selectByRoleId(roleId1);
		List<VendPermission> vendPermissions=new ArrayList<VendPermission>();
		for(VendRolePermission vendRolePermission:vendRolePermissions){
			VendPermission vendPermission=vendPermissionService.getOne(vendRolePermission.getPermissionId());
			vendPermissions.add(vendPermission);
		}
		VendUser vendUser=vendUserService.getOne(usercode);
		String permissionlist="";
		if(vendUser!=null){
			permissionlist=vendUser.getPermissionList();
			if(permissionlist==null){
				permissionlist="";
			}
		}
		
		List<ZNode> list=new ArrayList<ZNode>();
		for(VendPermission vendPermission:vendPermissions){
			ZNode zNode=new ZNode();
			zNode.setId(vendPermission.getId());
			zNode.setpId(vendPermission.getParentId());
			zNode.setName(vendPermission.getPermissionDescription());
			zNode.setOpen(true);
			if(permissionlist.indexOf(vendPermission.getId().toString()+",")!=-1){
				zNode.setChecked(true);
			}else{
				zNode.setChecked(false);
			}
		    list.add(zNode);
		}
	
		JSONArray json = JSONArray.fromObject(list);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.print(json);
	}
	/**
	 * 跳转用户权限添加界面
	 * @param model
	 * @param usercode
	 * @return
	 */
	@RequiresPermissions({"user:addpermission"})
	@RequestMapping(value="/{roleId}/{usercode}/addpermission",method=RequestMethod.GET)
	public String addpermission(Model model,@PathVariable Integer roleId,@PathVariable String usercode){
		VendUser vendUser=new VendUser();
		vendUser.setUsercode(usercode);
		vendUser.setRoleId(roleId);
		model.addAttribute(vendUser);
		return "manage/user/user_permission_add";
	}
	 /**
	   * 用户权限设置
	   * @param model
	   * @param id
	   * @return
	   */
	 @RequestMapping(value="/addpermission",method=RequestMethod.POST)
	 public String setmenu(HttpServletRequest request){
		String usercode=request.getParameter("usercode");
		String permissionList=request.getParameter("nodeIds");
		
		VendUser vendUser=vendUserService.getOne(usercode);
		if(vendUser!=null){
			vendUser.setPermissionList(permissionList);
			int isOk=vendUserService.editVendUser(vendUser);
			if(isOk==1){
				CacheUtils.remove("userCache", "key_usergetRoles"+vendUser.getUsername());
			}
		}
		return "redirect:/user/users";
	 }
	/**
	 * 跳转用户信息添加界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"user:add"})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpServletRequest request,Model model){
		HttpSession session=request.getSession();
    	VendUser user=(VendUser)session.getAttribute("vendUser");
		List<VendRole> roles=vendRoleService.findNextAllNOTSELF(user.getRoleId());//下级角色列表
		model.addAttribute("roles",roles);
		model.addAttribute(new VendUser());
		return "manage/user/user_add";
	}
   /**
    * 添加用户信息
    * @param request
    * @param model
    * @param vendUser
    * @param br
    * @return
    */
	@RequiresPermissions({"user:add"})
    @RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request,Model model,@Validated VendUser vendUser,BindingResult br){
		HttpSession session=request.getSession();
		VendUser user=(VendUser)session.getAttribute("vendUser");
		if(user!=null){//上级账号
			vendUser.setParentUsercode(user.getUsercode());
			List<VendRole> roles=vendRoleService.findNextAllNOTSELF(user.getRoleId());//角色列表
			model.addAttribute("roles",roles);
		}
		VendUser pvendUser=vendUserService.selectByUsername(vendUser.getUsername());
		if(pvendUser!=null){
			br.rejectValue("username", "NOREPEAT", "用户名不能重复");
		}
    	if(br.hasErrors()){
    		return "manage/user/user_add";
    	}
    	String password=Function.getEncrypt(vendUser.getPassword());//密码加密
    	vendUser.setPassword(password);
    	vendUserService.insertVendUser(vendUser);
    	return "redirect:users";
	}
    /**
     * 跳转用户修改界面
     * @param request
     * @param model
     * @param usercode
     * @return
     */
	@RequiresPermissions({"user:edit"})
	@RequestMapping(value="/{usercode}/edit",method=RequestMethod.GET)
	public String edit(HttpServletRequest request,Model model,@PathVariable String usercode){
		HttpSession session=request.getSession();
		VendUser user=(VendUser)session.getAttribute("vendUser");
		if(user!=null){//上级账号
			List<VendRole> roles=vendRoleService.findNextAllNOTSELF(user.getRoleId());//角色列表
			model.addAttribute("roles",roles);
		}
		VendUser vendUser=vendUserService.getOne(usercode);
		model.addAttribute(vendUser);
		return "manage/user/user_edit";
	}
	/**
	 * 修改用户信息
	 * @param model
	 * @param vendUser
	 * @param br
	 * @return
	 */
	@RequiresPermissions({"user:edit"})
    @RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(HttpServletRequest request,Model model,@Validated VendUser vendUser,BindingResult br){
		HttpSession session=request.getSession();
		VendUser user=(VendUser)session.getAttribute("vendUser");
		if(user!=null){//上级账号
			List<VendRole> roles=vendRoleService.findNextAllNOTSELF(user.getRoleId());//角色列表
			model.addAttribute("roles",roles);
		}
    	if(br.hasErrors()){
    		return "manage/user/user_edit";
    	}
    	vendUserService.editVendUser(vendUser);
		return "redirect:users";
	}
    /**
     * 删除用户信息
     * @param user
     * @param br
     * @return
     */
	@RequiresPermissions({"user:del"})
    @RequestMapping(value="/{usercode}/del")
 	public String del(@PathVariable String usercode){
    	vendUserService.delVendUser(usercode);
 		return "redirect:/user/users";
 	}
    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
	@RequiresPermissions({"user:dels"})
    @RequestMapping(value="/dels")
  	public String dels(HttpServletRequest request){
    	String usercodes=request.getParameter("ids");
    	String usercodeArray[]=usercodes.split(",");
    	vendUserService.delVendUsers(usercodeArray);
  		return "redirect:/user/users";
  	}
	 /**
	  * 个人中心
	  * @param model
	  * @return
	  */
    @RequestMapping(value="/self")
  	public String ownself(Model model){
    	model.addAttribute(new VendUser());
  		return "manage/user/self";
  	}
    /**
	 * 修改用户信息
	 * @param model
	 * @param vendUser
	 * @param br
	 * @return
	 */
    @RequestMapping(value="/editself",method=RequestMethod.POST)
	public String self(Model model,@Validated VendUser vendUser,BindingResult br){
    	List<VendRole> userroles=vendRoleService.findAll();
		model.addAttribute("userroles", userroles);
    	if(br.hasErrors()){
    		return "manage/user/self";
    	}
    	vendUserService.editVendUser(vendUser);
		return "manage/user/self";
	}
    /**
	  * 跳转到修改密码界面
	  * @param model
	  * @return
	  */
   @RequestMapping(value="/editpwd",method=RequestMethod.GET)
 	public String editPassword(Model model){
	   model.addAttribute(new VendUser());
 		return "manage/user/editpwd";
 	}
   /**
    * 修改密码
    * @param usercode
    * @param model
    * @return
    */
    @RequestMapping(value="/editpwd",method=RequestMethod.POST)
    public String editPassword(Model model,@Validated VendUser vendUser,BindingResult br){
    	VendUser pvendUser=vendUserService.getOne(vendUser.getUsercode());
    	String extend3=Function.getEncrypt(vendUser.getExtend3());
    	if(!extend3.equals(pvendUser.getPassword())){
    		br.rejectValue("extend3", "NOTEQUAL", "原密码不匹配");
    	}
    	if(extend3.equals(vendUser.getPassword())){
    		br.rejectValue("extend3", "NOTEQUAL", "新密码与原密码相同");
    	}
    	if(br.hasErrors()){
    		return "manage/user/editpwd";
    	}
    	pvendUser.setPassword(extend3);
    	model.addAttribute(pvendUser);
    	return "manage/user/editpwd";
    }
    /**
	 * 小程序消费用户修改密码
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/useeditpwd",method=RequestMethod.POST,produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody void editPwd(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		json.put("success", "0");
		json.put("msg", "修改失败");
		String usercode = request.getParameter("usercode");
		String oldpassword = request.getParameter("oldpassword");
	    String newpassword = request.getParameter("newpassword");
	    VendUser vendUser=vendUserService.getOne(usercode);

	    if(vendUser!=null){
	    	if(!Function.getEncrypt(oldpassword).equals(vendUser.getPassword())){
	    		json.put("success", "0");
	    		json.put("msg", "原密码不匹配");
	    	}else if(oldpassword.equals(newpassword)){
	    		json.put("success", "0");
	    		json.put("msg", "新密码与原密码相同");
	    	}else{
	    		String password=Function.getEncrypt(newpassword);
	    		vendUser.setPassword(password);
	    		int isOk=vendUserService.editVendUser(vendUser);
	    		if(isOk==1){
	    			json.put("success", "1");
		    		json.put("msg", "修改成功");
	    		}
	    	}
	    }
	    try {
	    	response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
