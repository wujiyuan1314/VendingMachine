package vend.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import base.util.DateUtil;
import base.util.FileUploadUtils;

@Controller
@RequestMapping("/upload")
public class UploadController extends LogoutFilter{
	public static Logger logger = Logger.getLogger(UploadController.class);
	
	@RequestMapping(value="/pic",method=RequestMethod.POST)
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
	@RequestMapping(value="/pic1",method=RequestMethod.POST)
    public void uploadpic1(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws IOException{
		String storePath="/userfiles/adpic";
		String originalFileName = file.getOriginalFilename();
        
        String path =request.getSession().getServletContext().getRealPath(storePath);
        //得到存储到本地的文件名
        String localFileName=DateUtil.getCurrentDateTimeKeyStr()+FileUploadUtils.getFileSuffix(originalFileName);
        int num=path.indexOf(".metadata");
        String basePath=path.substring(0,num)+request.getContextPath()+"/src/main/webapp"+storePath;
        //新建本地文件
        File localFile = new File(basePath,localFileName);
        //创建目录
        File fileDir=new File(path);
        if (!fileDir.exists()) {
        	fileDir.mkdirs();
        }
        String src=storePath+"/"+localFileName;
        // 保存
        try {
            file.transferTo(fileDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.print(src);
    }
}
