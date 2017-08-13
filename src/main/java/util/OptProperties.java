package util;
import java.util.*;
import java.io.*;
/**
 * 读取格式为.properties的文件内容的类
 * @author ylsoft
 *
 */
public class OptProperties {
static Properties ctx =new  Properties();
/*
 * 读取propFile文件的属性值
 */
public static String getPropertyValue(String propFile,String key){
	Properties ctx =new  Properties();
	String value="";
	try{
		ClassLoader classLoader=OptProperties.class.getClassLoader();
		ctx=new Properties();
		ctx.load(classLoader.getResourceAsStream(propFile));
		value =ctx.getProperty(key);
	}catch(Exception e){
		e.printStackTrace();
		
	}
	return value;
}
/*
 * 写propFile属性值
 */
public static   boolean  writePropertyValue(String propFile,String key,String value) {
	 boolean isSuccess=false;
	 String classPath=(new OptProperties()).getClass().getClassLoader().getResource(".").getPath();
	 propFile=classPath+propFile;
	try {
		InputStream fis = new FileInputStream(propFile);
		ctx.load(fis);
		OutputStream fos = new FileOutputStream(propFile);
		ctx.setProperty(key, value);
		ctx.store(fos, key);
		isSuccess=true;
	}
	catch (Exception e) {
		System.out.println(e.toString());
		value="";
	}
	return isSuccess;
}
}
