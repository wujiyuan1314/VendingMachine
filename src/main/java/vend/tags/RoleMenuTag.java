package vend.tags;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import vend.entity.Menuitem;
import vend.entity.VendRole;
import vend.service.MenuitemService;
import vend.service.VendRoleService;

public class RoleMenuTag extends RequestContextAwareTag{
	private static final long serialVersionUID = 1L;
	private int parentId;
	private Integer roleid;
	MenuitemService menuitemService;
	VendRoleService vendRoleService;
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	@Override
	public int doStartTagInternal() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		try{
			menuitemService=this.getRequestContext().getWebApplicationContext().getBean(MenuitemService.class);
			vendRoleService=this.getRequestContext().getWebApplicationContext().getBean(VendRoleService.class);
			List<Menuitem> childrenmenus=menuitemService.selectByParentId(parentId);
			List<Menuitem> rolemenus=new ArrayList<Menuitem>();
			VendRole vendRole=vendRoleService.getOne(roleid);
			String menuList="";
			if(vendRole!=null){
				menuList=vendRole.getExtend1();
				if(menuList!=null){
					for(Menuitem menu:childrenmenus){
						String menuid=menu.getId().toString();
						if(menuList.indexOf(menuid)!=-1){
							menu.setExtend2("1");
							rolemenus.add(menu);
						}
					}
				}
			}
			request.setAttribute("rolemenus", rolemenus);
			request.setAttribute("childrenmenus", childrenmenus);
		}catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
	//--------------------------
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
}
