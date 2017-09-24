package vend.tags;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import vend.entity.CodeLibrary;
import vend.service.CodeLibraryService;

public class CodeTag extends RequestContextAwareTag{
	private static final long serialVersionUID = 1L;
	private String codeno="";//编码类型
	private String itemno="";//编码编号
	static CodeLibraryService codeLibraryService;
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	@Override
	public int doStartTagInternal() throws JspException {
		try{
			codeLibraryService=this.getRequestContext().getWebApplicationContext().getBean(CodeLibraryService.class);
			JspWriter out = pageContext.getOut();
			List<CodeLibrary> list=codeLibraryService.selectByCodeNo(codeno);
			String itemname="";
			for(CodeLibrary code:list){
				if(code.getItemno().equals(itemno)){
					itemname=code.getItemname();
				}else{
					continue;
				}
			}
			out.print(itemname);
		}catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
	//--------------------------
	public String getCodeno() {
		return codeno;
	}
	public void setCodeno(String codeno) {
		this.codeno = codeno;
	}
	public String getItemno() {
		return itemno;
	}
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
	
}
