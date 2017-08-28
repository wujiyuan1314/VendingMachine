package vend.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import base.util.DateUtil;
import base.util.FileUploadUtils;
import base.util.Function;
import vend.entity.CodeLibrary;
import vend.service.CodeLibraryService;

@Controller
@RequestMapping("/upload")
public class UploadController extends LogoutFilter{
	public static Logger logger = Logger.getLogger(UploadController.class);
	
	@Autowired
	CodeLibraryService codeLibraryService;
	
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
	/**
	 * 上传图片
	 * @param request
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/pic1",method=RequestMethod.POST)
    public String uploadpic1(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		String json="{success:0,msg:'上传失败'}";
		if(file.isEmpty()){
			json="{success:0,msg:'上传的图片为空'}";
			out.print(json);
			return null;
		}
		List<CodeLibrary> list=codeLibraryService.selectByCodeNo("UPPICTYPE");
		
		String type=file.getContentType();
		String typeArray[]=Function.stringSpilit(type, "/");
		int flag=0;
		if(typeArray.length>=2){
			for(CodeLibrary code:list){
				if(code.getItemname().equalsIgnoreCase(typeArray[1])){
					flag=1;
					break;
				}
			}
		}
		if(flag==0){
			json="{success:0,msg:'不允许的上传类型'}";
			out.print(json);
			return null;	
		}
		
		long size=file.getSize();
		if(size>314572800){
			json="{success:0,msg:'上传文件大小超过限制'}";
			out.print(json);
			return null;
		}
		
		String storePath="/userfiles/pic";
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
            file.transferTo(localFile);
            json="{success:1,msg:'"+src+"'}";
        } catch (Exception e) {
        	json="{success:0,msg:'上传失败'}";
            e.printStackTrace();
        }
        
        out.print(json);
        return null;
    }
	/**
	 * 上传文件
	 * @param request
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/file",method=RequestMethod.POST)
    public String uploadfile(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		String json="{success:0,msg:'上传失败'}";
		if(file.isEmpty()){
			json="{success:0,msg:'上传的文件为空'}";
			out.print(json);
			return null;
		}
		List<CodeLibrary> list=codeLibraryService.selectByCodeNo("UPFILETYPE");
		
		String type=file.getContentType();
		String typeArray[]=Function.stringSpilit(type, "/");
		int flag=0;
		if(typeArray.length>=2){
			for(CodeLibrary code:list){
				if(code.getItemname().equalsIgnoreCase(typeArray[1])){
					flag=1;
					break;
				}
			}
		}
		if(flag==0){
			json="{success:0,msg:'不允许的上传类型'}";
			out.print(json);
			return null;	
		}
		
		long size=file.getSize();
		if(size>314572800){
			json="{success:0,msg:'上传文件大小超过限制'}";
			out.print(json);
			return null;
		}
		
		String storePath="/userfiles/file";
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
            file.transferTo(localFile);
            json="{success:1,msg:'"+src+"'}";
        } catch (Exception e) {
        	json="{success:0,msg:'上传失败'}";
            e.printStackTrace();
        }
        
        out.print(json);
        return null;
    }
	/**
	 * 上传视频
	 * @param request
	 * @param file
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/video",method=RequestMethod.POST)
    public String uploadvideo(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		String json="{success:0,msg:'上传失败'}";
		if(file.isEmpty()){
			json="{success:0,msg:'上传的文件为空'}";
			out.print(json);
			return null;
		}
		List<CodeLibrary> list=codeLibraryService.selectByCodeNo("UPVIDEOTYPE");
		
		String type=file.getContentType();
		String typeArray[]=Function.stringSpilit(type, "/");
		int flag=0;
		if(typeArray.length>=2){
			for(CodeLibrary code:list){
				if(code.getItemname().equalsIgnoreCase(typeArray[1])){
					flag=1;
					break;
				}
			}
		}
		if(flag==0){
			json="{success:0,msg:'不允许的上传类型'}";
			out.print(json);
			return null;	
		}
		
		long size=file.getSize();
		if(size>314572800){
			json="{success:0,msg:'上传文件大小超过限制'}";
			out.print(json);
			return null;
		}
		
		String storePath="/userfiles/video";
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
            file.transferTo(localFile);
            json="{success:1,msg:'"+src+"'}";
        } catch (Exception e) {
        	json="{success:0,msg:'上传失败'}";
            e.printStackTrace();
        }
        
        out.print(json);
        return null;
    }
}
