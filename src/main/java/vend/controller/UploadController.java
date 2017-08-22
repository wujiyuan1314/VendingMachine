package vend.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import base.util.FileUploadUtils;

@Controller
@RequestMapping("/upload")
public class UploadController extends LogoutFilter{
	public static Logger logger = Logger.getLogger(UploadController.class);
	
	@RequestMapping(value="/pic")
    public void uploadpic(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String path=request.getParameter("path");
		HttpSession session=request.getSession();
		session.setAttribute("path", path);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String filepath=FileUploadUtils.tranferFile(request, "/userfiles/pic");
		String json="{filepath:\""+filepath+"\"}";
		out.print(json);
    }	 
}
