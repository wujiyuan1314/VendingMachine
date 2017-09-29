

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import base.util.EncodeUtils;
import base.util.Function;
import base.util.HttpClientUtil;

public class Test {
    public static void path(HttpServletRequest request){
    	System.out.println(request.getPathInfo());
        System.out.println(request.getPathTranslated());
        System.out.println(request.getServletPath());
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*HttpServletRequest request = null;
		String ids="";
		String idArray[]=ids.split("3");
		System.out.println(idArray.length);
		//.out.println(idArray[2]);
		for(int i=0;i<idArray.length;i++){
			System.out.println(idArray[i]+"ff");
		}
    	int[] idArray1=new int[idArray.length];
    	
    	//String chars = "abcdefghijklmnopqrstuvwxyz";
    	//System.out.println(chars.charAt((int)(Math.random() * 26)));*/
		File file=new File("F:/workspace_tmms/VendingMachine/src/main/webapp/userfiles/pic/xmgz.jpg");
		long filesize=0;
		if (file.exists() && file.isFile()){  
			filesize=file.length();
			System.out.println(file.getParentFile());
			System.out.println(file.getName());
			System.out.println(file.getParent());
			try {
				System.out.println(file.getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(file.getPath());
			System.out.println(file.getAbsolutePath());
			System.out.println(filesize);
	    }
		String redirect_uri=EncodeUtils.urlEncode("http://zdypx.benbenniaokeji.com");
		System.out.println(redirect_uri);
		
		String urlStr="https://api.weixin.qq.com/sns/userinfo?access_token=T5Q0yZU-2NMq0IxfU8ZVQmd65cp4qGydXIFbqA-12yqHrm00xMoxcNfhlx6LCaOpWhcbOCnZCYduLjdvVDFztg&openid=oz3YAuOV9V-lEkVZAUn8KrzPpcOY&lang=zh_CN";
		
		String retMsg=HttpClientUtil.httpGetRequest(urlStr);
		System.out.println(retMsg);
		
		URL url;
		URI uri = null;
		try {
			url = new URL(urlStr);
			uri = new URI(url.getProtocol(),url.getHost(),url.getPath(),url.getQuery(),null);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		retMsg=HttpClientUtil.httpGetRequest(uri);
		System.out.println(retMsg);
		//System.out.println(m.indexOf("ghjgj,"));
		
	}

}
