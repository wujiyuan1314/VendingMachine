package util;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
/**
 * 处理字符串相关工具类
 * @author ylsoft
 *
 */
public class Function {
//得到32位序列
public static String getUUID(){
	String uuid=Function.ClearTrim(UUID.randomUUID()).replaceAll("-", "");
	return uuid;
}
//清除字符串两边的空格
public static String ClearTrim(Object obj){
	String result="";
	if(obj!=null)result=obj.toString();
	if(result!=null)result=result.trim();
	if("null".equals(obj)) return "";
    return result;
}
//清除字符串两边的空格，并给默认值
public static String getDefaultStr(Object obj,String val){
	String result="";
	if(obj!=null)result=obj.toString();
	if(result!=null)result=result.trim();
	if("null".equals(obj)) return val;
	if("".equals(result)){
		return val;
	}else {
		return result;
	}
}
//把对象转化为整数，非整数则返回defaultNum
public static int getInt(Object obj,int defaultNum){
	String value=ClearTrim(obj);
	int num=defaultNum;
	if(value!=null&&!value.equals("")){
		try{num=Integer.parseInt(value);}catch(Exception ignored){}
	}
	return num;
}
//把对象转化为长整数，非整数则返回defaultNum
public static Long getLong(Object obj,Long defaultNum){
	String value=ClearTrim(obj);
	Long num=defaultNum;
	if(value!=null&&!value.equals("")){
		try{num=Long.parseLong(value);}catch(Exception ignored){}
	}
	return num;
}
//把对象转化为浮点，非浮点则返回defaultNum
public static double getFloat(Object obj,Float defaultNum){
	Float num=defaultNum;
	String value=ClearTrim(obj);
	if(value!=null&&!value.equals("")){
		try{num=Float.valueOf(ClearTrim(value));}catch(Exception ignored){}
	}
	return num;
}
//把对象转化为双精度，非双精度则返回defaultNum
public static double getDouble(Object obj,Double defaultNum){
	Double num=defaultNum;
	String value=ClearTrim(obj);
	if(value!=null&&!value.equals("")){
		try{num=Double.valueOf(ClearTrim(value));}catch(Exception ignored){}
	}
	return num;
}
public static String getYuan(Object yuan){
	double yuan1=Function.getDouble(yuan, 0.00);
	 String f1=String.format("%.2f", yuan1);
	 //DecimalFormat df = new DecimalFormat("#.00");
	 //String f1=df.format(yuan);
	return f1;
}	
public static String getYuanByFen(Long fen){
	double yuan=Function.getDouble(Function.ClearTrim(fen), 0.00)/100;
	 String f1=String.format("%.2f", yuan);
	 //DecimalFormat df = new DecimalFormat("#.00");
	 //String f1=df.format(yuan);
	return f1;
}	
public static Long getFenByYuan(double yuan){
	BigDecimal bg = new BigDecimal(yuan);
    double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    Long fen=Long.parseLong(new java.text.DecimalFormat("0").format(f1*100));
	return fen;
}
public static int getIntFenByYuan(double yuan){
	BigDecimal bg = new BigDecimal(yuan);
    double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    int fen=Integer.parseInt(new java.text.DecimalFormat("0").format(f1*100));
	return fen;
}

public static String getTitle(String title,int titlecount){
	title=Function.ClearTrim(title);
	if(title.length()>titlecount){
		title=title.substring(0, titlecount);
	}
	return title;
}
/**   
 * 分割字符串   
  * @param str 要分割的字符串   
   * @param spilit_sign 字符串的分割标志  
    * @return 分割后得到的字符串数组  
     */   
 public static String[] stringSpilit(String str,String spilit_sign){   
	   str=ClearTrim(str);
       String[] spilit_string=str.split(spilit_sign);   
        if(spilit_string[0].equals("")){   
            String[] new_string=new String[spilit_string.length-1];  
            for(int i=1;i<spilit_string.length;i++) 
	               new_string[i-1]=spilit_string[i]; 
              return new_string;   
          } else{  
            return spilit_string;  
          }
    }
public static boolean isExit(String value,String strList){
	value=Function.ClearTrim(value);
	strList=Function.ClearTrim(strList);
	boolean isOk=false;
	String strArray[]=strList.split(",");
	for(int i=0;i<strArray.length;i++){
		if(value.equals(strArray[i])){
			isOk=true;
			break;
		}
	}
	return isOk;
}
//特殊字符过滤
public static String CheckReplace(String s)
{ 
    try
    {
	    if(s == null || s.equals("")) return "";
		else
		{
			StringBuffer stringbuffer = new StringBuffer();
			for(int i = 0; i < s.length(); i++)
			{
				char c = s.charAt(i);
				switch(c)
				{							
				 case 10:
					 //stringbuffer.append("<br>");
					 stringbuffer.append(" ");
				  break;
				  case 13:
					  //stringbuffer.append("<br>");
					  stringbuffer.append(" ");
				  break;					
				case 34: // '"'
					stringbuffer.append("&quot;");
					break;
	
				case 39: // '\''									
					stringbuffer.append("&#039;");
					break;

//				case 124: // '|'
//					stringbuffer.append("");
//					break;
				case 92:// '\'
					stringbuffer.append("/");
					break;
				case '&':
					stringbuffer.append("&amp;");
					break;
				
				case '<':
					stringbuffer.append("&lt;");
					break;
				
				case '>':
					stringbuffer.append("&gt;");
					break;
				
				default:
					stringbuffer.append(c);
					break;
				}
			}
			return stringbuffer.toString().trim();		
			}
		}catch(Exception e){ 
			return "";
		}
}

public static String  getEncrypt(String value){//加密
	 String result="";
	 String key="ylsoft_"+DateUtil.getCurrentDateStr();
	 try {
		 DESPlus des = new DESPlus(key);// 自定义密钥
		 result=des.encrypt(value);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
public static String  getDecrypt(String value){//解密
	 String result="";
	 String key="ylsoft_"+DateUtil.getCurrentDateStr();
	 try {
		 DESPlus des = new DESPlus(key);// 自定义密钥
		 result= des.decrypt(value);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

//根据key值判断在map中的对应的值，并返回结果
public static int getValue(String str,Map<String, String> map){
	int result = 0;
	Iterator<String> keys = map.keySet().iterator();
	while(keys.hasNext()){
		String key = (String)keys.next();
		if(str.equals(key)){
			result = 1;
		}
	}
	return result;
}
/**
 * 比较两个日期大小
 */
public static int compare_date(String DATE1, String DATE2) {
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
     try {
         Date dt1 = df.parse(DATE1);
         Date dt2 = df.parse(DATE2);
         if (dt1.getTime() > dt2.getTime()) {
             System.out.println("dt1 在dt2前");
             return 1;
         } else if (dt1.getTime() < dt2.getTime()) {
             System.out.println("dt1在dt2后");
             return -1;
         } else {
             return 0;
         }
     }catch (Exception exception) {
         exception.printStackTrace();
     }
     return 0;
     
}

/**
 * 获取数组中最大的值
 */
public static int getMax(int c[]) {  
    int len = c.length;  
    int max = c[0];  
    for(int i=1;i<len;i++) {  
        if(max<c[i]) {  
            max = c[i];  
        }  
    }  
    return max;  
}
/**
 * 获取连续数组中缺失的元素
 */
public static void findMissElement(int b[]) {  
    int len = b.length;  
    int max = getMax(b);  
    int c[] = new int[max+1];  
    for(int i=0;i<len;i++) {  
        ++c[b[i]];  
    }  
    for(int i=0;i<max+1;i++) {  
        if(c[i]==0&&i!=0) {  
            System.out.println("[丢失的元素是"+i+"]");  
        }  
    }  
}  

/**
 * 获取连续数组中重复的元素
 */
public static void findRepeatElement(int b[]) {  
    int len = b.length;  
    int max = getMax(b);  
    int c[] = new int[max+1];  
    for(int i=0;i<len;i++) {  
        ++c[b[i]];  
    }  
    for(int i=0;i<max+1;i++) {  
        if(c[i]==2&&i!=0) {  
            System.out.println("[重复的元素是"+i+"]");  
        }  
    }  
}  

public static String getYuanByFens(Long fen){
	double yuan=Function.getDouble(Function.ClearTrim(fen), 0.00)/100;
	String f1=String.format("%.2f", yuan);
	if(f1.endsWith(".00")){
		f1=f1.substring(0,f1.indexOf("."));
	}
	return f1;
}	

}


