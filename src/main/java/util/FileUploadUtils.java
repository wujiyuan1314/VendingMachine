package util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class FileUploadUtils {
	  /**
     * 
     * @param request
     * @param storePath 文件存储相对路径 ,例如："/upload","/image","/local/file"
     * @return
     */
    public static String tranferFile(HttpServletRequest request,String storePath){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); 
        //先判断request中是否包涵multipart类型的数据，
        if(multipartResolver.isMultipart(request)){
            //再将request中的数据转化成multipart类型的数据
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                MultipartFile file = multiRequest.getFile((String)iter.next());
                if(file != null){
                    String originalFileName = file.getOriginalFilename();
                    
                    String path =request.getSession().getServletContext().getRealPath(storePath);
                    //得到存储到本地的文件名
                    String localFileName=UUID.randomUUID().toString()+getFileSuffix(originalFileName);
                    int num=path.indexOf(".metadata");
                    String basePath=path.substring(0,num)+request.getContextPath()+"/src/main/webapp"+storePath;
                    //新建本地文件
                    File localFile = new File(basePath,localFileName);
                    //创建目录
                    File fileDir=new File(path);
                    if (!fileDir.isDirectory()) {
                        // 如果目录不存在，则创建目录
                        fileDir.mkdirs();
                    }
                    
                    
                    String src=basePath+"/"+localFileName;
                    //写文件到本地
                    try {
                        file.transferTo(localFile);
                        return src;
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
    /**
     * 获取文件后缀
     * @param originalFileName
     * @return
     */
    public static String getFileSuffix(String originalFileName){
        int dot=originalFileName.lastIndexOf('.');
        if((dot>-1)&&(dot<originalFileName.length())){
            return originalFileName.substring(dot);
        }
        return originalFileName;
    }
}
