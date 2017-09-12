package vend.tags;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import vend.entity.VendRole;
import vend.service.VendRoleService;

public class RoleTag extends RequestContextAwareTag{
	private static final long serialVersionUID = 1L;
	private int roleid;//角色ID
	static VendRoleService vendRoleService;
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	@Override
	public int doStartTagInternal() throws JspException {
		try{
			vendRoleService=this.getRequestContext().getWebApplicationContext().getBean(VendRoleService.class);
			JspWriter out = pageContext.getOut();
			VendRole vendRole=vendRoleService.getOne(roleid);
			String rolename=vendRole.getRoleName();
			out.print(rolename);
		}catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
	//--------------------------
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
}
